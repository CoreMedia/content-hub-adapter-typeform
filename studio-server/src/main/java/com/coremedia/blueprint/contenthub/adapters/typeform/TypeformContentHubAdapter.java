package com.coremedia.blueprint.contenthub.adapters.typeform;


import com.coremedia.blueprint.contenthub.adapters.typeform.model.TypeformContentHubType;
import com.coremedia.blueprint.contenthub.adapters.typeform.model.TypeformFolder;
import com.coremedia.blueprint.contenthub.adapters.typeform.model.TypeformItem;
import com.coremedia.blueprint.contenthub.adapters.typeform.service.TypeformService;
import com.coremedia.blueprint.contenthub.adapters.typeform.service.model.Form;
import com.coremedia.contenthub.api.ContentHubAdapter;
import com.coremedia.contenthub.api.ContentHubContext;
import com.coremedia.contenthub.api.ContentHubObject;
import com.coremedia.contenthub.api.ContentHubObjectId;
import com.coremedia.contenthub.api.ContentHubTransformer;
import com.coremedia.contenthub.api.ContentHubType;
import com.coremedia.contenthub.api.Folder;
import com.coremedia.contenthub.api.GetChildrenResult;
import com.coremedia.contenthub.api.Item;
import com.coremedia.contenthub.api.exception.ContentHubException;
import com.coremedia.contenthub.api.pagination.PaginationRequest;
import com.coremedia.contenthub.api.search.ContentHubSearchResult;
import com.coremedia.contenthub.api.search.ContentHubSearchService;
import com.coremedia.contenthub.api.search.Sort;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class TypeformContentHubAdapter implements ContentHubAdapter, ContentHubSearchService {

  private static final Logger LOG = LoggerFactory.getLogger(TypeformContentHubAdapter.class);

  private final String connectionId;
  private final TypeformContentHubSettings settings;

  private final TypeformService typeformService;

  private final TypeformFolder rootFolder;

  public TypeformContentHubAdapter(TypeformContentHubSettings settings, String connectionId) {
    this.settings = settings;
    this.connectionId = connectionId;
    rootFolder = new TypeformFolder(new ContentHubObjectId(connectionId, "root"), settings.getDisplayName(), TypeformContentHubType.FOLDER);
    typeformService = new TypeformService(settings.getApiKey());
  }

  // --- ContentHubAdapter ---------------------------------------------------------------------------------------------

  @Override
  public Folder getRootFolder(ContentHubContext context) throws ContentHubException {
    return rootFolder;
  }

  @Nullable
  @Override
  public Folder getFolder(ContentHubContext context, ContentHubObjectId id) throws ContentHubException {
    return getRootFolder(context);
  }

  public List<Folder> getSubFolders(ContentHubContext context, Folder folder) throws ContentHubException {
    return Collections.emptyList();
  }

  @Nullable
  @Override
  public Folder getParent(ContentHubContext context, ContentHubObject contentHubObject) throws ContentHubException {
    return rootFolder == contentHubObject ? null : getRootFolder(context);
  }

  public List<Item> getItems(ContentHubContext context, Folder folder) throws ContentHubException {
    List<Item> items = Collections.emptyList();

    try {
      if (rootFolder == folder) {
        items = typeformService.getForms()
                .stream()
                .map(this::createFormItem)
                .collect(Collectors.toUnmodifiableList());
      }

    } catch (Exception e) {
      LOG.warn("Unable to get items for folder {}. {}", folder, e);
    }

    return items;
  }

  @Nullable
  @Override
  public Item getItem(ContentHubContext context, ContentHubObjectId id) throws ContentHubException {
    Item result;
    String[] externalId = id.getExternalId().split("-");
    result = Optional.ofNullable(typeformService.getFormById(externalId[1])).map(this::createFormItem).orElse(null);
    return result;
  }

  @Override
  public GetChildrenResult getChildren(ContentHubContext context, Folder folder, @Nullable PaginationRequest paginationRequest) {
    List<ContentHubObject> children = new ArrayList<>();
    children.addAll(getSubFolders(context, folder));
    children.addAll(getItems(context, folder));
    return new GetChildrenResult(children);
  }

  @Override
  public ContentHubTransformer transformer() {
    return new TypeformContentHubTransformer(settings.getEmbedCode());
  }


  // --- ContentHubSearchService ---------------------------------------------------------------------------------------

  private static final List<ContentHubType> SEARCH_TYPES = List.of(
          TypeformContentHubType.FORM.getType()
  );

  @NonNull
  @Override
  public Optional<ContentHubSearchService> searchService() {
    return Optional.of(this);
  }

  @Override
  public ContentHubSearchResult search(@NonNull String query,
                                       @Nullable Folder belowFolder,
                                       @Nullable ContentHubType type,
                                       Collection<String> filterQueries,
                                       List<Sort> sortCriteria,
                                       int limit) {

    ContentHubSearchResult result = new ContentHubSearchResult(Collections.emptyList());
    if (StringUtils.isBlank(query)) {
      return result;
    }
    return result;
  }

  @Override
  public boolean supportsSearchBelowFolder() {
    return true;
  }

  @Override
  public Collection<ContentHubType> supportedTypes() {
    return SEARCH_TYPES;
  }


  // --- INTERNAL ------------------------------------------------------------------------------------------------------

  private TypeformItem createFormItem(@NonNull Form form) {
    ContentHubObjectId hubId = new ContentHubObjectId(connectionId, "form-" + form.getId());
    return new TypeformItem(hubId, form, TypeformContentHubType.FORM);
  }
}
