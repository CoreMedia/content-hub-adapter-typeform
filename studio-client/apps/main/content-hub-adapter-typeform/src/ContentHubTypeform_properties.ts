import SvgIconUtil from "@coremedia/studio-client.cap-base-models/util/SvgIconUtil";
import icon from "./icons/typeform_16.svg";

interface ContentHubTypeform_properties {
  adapter_type_typeform_name: string;
  adapter_type_typeform_icon: string;
  item_type_form_name: string;
  item_type_form_icon: string;
}

const ContentHubTypeform_properties: ContentHubTypeform_properties = {
  adapter_type_typeform_name: "Typeform",
  adapter_type_typeform_icon: SvgIconUtil.getIconStyleClassForSvgIcon(icon),
  item_type_form_name: "Form",
  item_type_form_icon: SvgIconUtil.getIconStyleClassForSvgIcon(icon),
};

export default ContentHubTypeform_properties;
