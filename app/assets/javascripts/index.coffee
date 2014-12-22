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

      initCarousel()

  # Add the gallery carousel, if applicable.
  initCarousel = () ->
    carouselLinks = $('#links a')

    if carouselLinks.length > 0
      blueimp.Gallery carouselLinks, {
        container: '#carousel'
        carousel: true
        stretchImages: 'cover'
        startSlideshow: false
      }

  initCarousel()
  NProgress.configure { showSpinner: false }
