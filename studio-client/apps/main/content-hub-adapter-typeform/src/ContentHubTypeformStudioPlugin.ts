import ContentHub_properties from "@coremedia/studio-client.main.content-hub-editor-components/ContentHub_properties";
import CopyResourceBundleProperties from "@coremedia/studio-client.main.editor-components/configuration/CopyResourceBundleProperties";
import StudioPlugin from "@coremedia/studio-client.main.editor-components/configuration/StudioPlugin";
import Config from "@jangaroo/runtime/Config";
import ConfigUtils from "@jangaroo/runtime/ConfigUtils";
import resourceManager from "@jangaroo/runtime/l10n/resourceManager";
import ContentHubTypeform_properties from "./ContentHubTypeform_properties";

interface ContentHubTypeformStudioPluginConfig extends Config<StudioPlugin> {
}

class ContentHubTypeformStudioPlugin extends StudioPlugin {
  declare Config: ContentHubTypeformStudioPluginConfig;

  static readonly xtype: string = "com.coremedia.labs.plugins.adapters.typeform.client.ContentHubTypeformStudioPlugin";

  constructor(config: Config<ContentHubTypeformStudioPlugin> = null) {
    super(ConfigUtils.apply(Config(ContentHubTypeformStudioPlugin, {

      configuration: [
        new CopyResourceBundleProperties({
          destination: resourceManager.getResourceBundle(null, ContentHub_properties),
          source: resourceManager.getResourceBundle(null, ContentHubTypeform_properties),
        }),
      ],

    }), config));
  }
}

export default ContentHubTypeformStudioPlugin;
