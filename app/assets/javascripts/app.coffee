require.config {
  paths: {
    jquery: '../lib/jquery/jquery'
    nprogress: '../lib/nprogress/nprogress'
    async: '../lib/requirejs-plugins/async'
    gmaps: './vendor/gmaps'
    blueimp: 'blueimp-gallery-indicator'
  }
  shim: {
    nprogress: {
      deps: ['jquery']
    }
  }
}

require ['index', 'contact']
