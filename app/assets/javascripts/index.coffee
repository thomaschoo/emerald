define ['jquery', 'nprogress'], () ->

  $('li.menu-item').hover ->
    $(this).css 'cursor', 'pointer'
    $div = $(this).children 'div'
    $div.removeClass('menu-colour').addClass 'menu-hover-colour'
    $div.children('img').css 'visibility', 'visible'
  , ->
    $div = $(this).children 'div'
    $div.removeClass('menu-hover-colour').addClass 'menu-colour'
    $div.children('img').css 'visibility', 'hidden'

  $('li.menu-item').on 'click', () ->
    url = '/' + $(this).data 'url'
    NProgress.start()
    $.get url, (data) ->
      $('#main-content').html data
      NProgress.done()
      window.scrollTo 0, 0

      initCarousel url

  # Add the gallery carousel, if applicable.
  initCarousel = (path) ->
    carouselLinks = $('#links a')

    if carouselLinks.length > 0
      controller = path.replace '/', ''
      require [controller], (controller) ->
        controller.initCarousel carouselLinks, '#carousel'

  initCarousel window.location.pathname
  NProgress.configure { showSpinner: false }
