define ['jquery', 'nprogress'], () ->

  NProgress.configure { showSpinner: false }
  NProgress.start()

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
    NProgress.start()

    url = '/' + $(this).data 'url'
    $.get url, (data) ->
      $('#main-content').html data
      window.scrollTo 0, 0

      initCarousel()
      initLightbox()

      NProgress.done()

  # Add the carousel, if applicable.
  initCarousel = () ->
    carouselLinks = $('#links a')

    if carouselLinks.length > 0
      blueimp.Gallery carouselLinks, {
        container: '#carousel'
        carousel: true
        stretchImages: 'cover'
        startSlideshow: false
        onopened: () ->
          $('.prev').css 'display', 'inline'
          $('.next').css 'display', 'inline'
          $('.indicator').css 'display', 'inline'
      }

  # Add the lightbox, if applicable.
  initLightbox = () ->
    $('#lightbox-links').on 'click', (event) ->
      event = event || window.event
      target = event.target || event.srcElement
      link = if target.src then target.parentNode else target
      links = $(this).children 'a'
      blueimp.Gallery links, {
        container: '#carousel'
        index: link
        event: event
        slideshowInterval: 3000
      }

  initCarousel()
  initLightbox()

  NProgress.done()
