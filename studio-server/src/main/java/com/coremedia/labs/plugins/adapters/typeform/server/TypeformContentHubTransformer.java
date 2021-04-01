package com.coremedia.labs.plugins.adapters.typeform.server;

import com.coremedia.contenthub.api.ContentHubAdapter;
import com.coremedia.contenthub.api.ContentHubContext;
import com.coremedia.contenthub.api.ContentHubTransformer;
import com.coremedia.contenthub.api.ContentModel;
import com.coremedia.contenthub.api.Item;
import com.coremedia.labs.plugins.adapters.typeform.server.model.TypeformItem;
import com.coremedia.xml.Markup;
import com.coremedia.xml.MarkupFactory;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TypeformContentHubTransformer implements ContentHubTransformer {

  private static final String MARKUP_TEMPLATE = "<?xml version=\"1.0\" ?><div xmlns=\"http://www.coremedia.com/2003/richtext-1.0\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"><p>%s</p></div>";
  private static final Logger LOG = LoggerFactory.getLogger(TypeformContentHubTransformer.class);
  private final String embedCodeTemplate;

  public TypeformContentHubTransformer(String embedCodeTemplate) {
    this.embedCodeTemplate = embedCodeTemplate;
  }

  @Nullable
  @Override
  public ContentModel transform(Item source, ContentHubAdapter contentHubAdapter, ContentHubContext contentHubContext) {
    if (!(source instanceof TypeformItem)) {
      throw new IllegalArgumentException("Cannot transform source " + source);
    }

    TypeformItem item = (TypeformItem) source;
    LOG.info("Creating content model for item {}.", item);

    ContentModel contentModel = ContentModel.createContentModel(item);
    contentModel.put("title", item.getName());
    if (StringUtils.isNotBlank(embedCodeTemplate) && StringUtils.isNotBlank(item.getEmbedCode())) {
      String embedCode = String.format(embedCodeTemplate, item.getEmbedCode());
      if (StringUtils.isNoneBlank(embedCode)) {
        String markup = String.format(MARKUP_TEMPLATE, StringEscapeUtils.escapeHtml4(embedCode));
        Markup codeToEmbed = MarkupFactory.fromString(markup);
        contentModel.put("data", codeToEmbed);
      }
    }

    return contentModel;
  }
}
