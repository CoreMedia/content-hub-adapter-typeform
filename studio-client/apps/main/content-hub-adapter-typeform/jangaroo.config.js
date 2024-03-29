const { jangarooConfig } = require("@jangaroo/core");

module.exports = jangarooConfig({
  type: "code",
  sencha: {
    name: "com.coremedia.labs.plugins__studio-client.content-hub-adapter-typeform",
    namespace: "com.coremedia.labs.plugins.adapters.typeform.client",
    studioPlugins: [
      {
        mainClass: "com.coremedia.labs.plugins.adapters.typeform.client.ContentHubTypeformStudioPlugin",
        name: "Content Hub",
      },
    ],
  },
  command: {
    build: {
      ignoreTypeErrors: true
    },
  },
});
