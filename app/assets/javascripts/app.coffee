require.config {
  paths: {
    jquery: '../lib/jquery/jquery'
    nprogress: '../lib/nprogress/nprogress'
    async: '../lib/requirejs-plugins/async'
    gmaps: './vendor/gmaps'
  }
  shim: {
    jquery: {
      exports: '$'
    }
    nprogress: {
      deps: ['jquery']
    }
  }
}

require ['index', 'contact']
