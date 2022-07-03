const baseConfig = require('@eclipse-scout/cli/scripts/webpack-defaults');

module.exports = (env, args) => {
  args.resDirArray = ['src/main/resources/WebContent', 'node_modules/@eclipse-scout/core/res'];
  const config = baseConfig(env, args);

  config.entry = {
    'xlsxreader': './src/main/js/xlsxreader.js',
    'login': './src/main/js/login.js',
    'logout': './src/main/js/logout.js',
    'xlsxreader-theme': './src/main/js/xlsxreader-theme.less',
    'xlsxreader-theme-dark': './src/main/js/xlsxreader-theme-dark.less'
  };

  return config;
};
