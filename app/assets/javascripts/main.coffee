require.config {
  paths: {
    jquery: '../lib/jquery/jquery'
    nprogress: '../lib/nprogress/nprogress'
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

require ['index']
