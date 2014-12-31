define ['jquery', 'nprogress'], () ->

  NProgress.configure { showSpinner: false }
  NProgress.start()

  altRootPathname = '/home'

  $('li.menu-item').on 'click', () ->
    pathname = $(this).data 'pathname'
    currentPathname = window.location.pathname
    if !isSamePathname currentPathname, pathname
      window.history.pushState { pathname: pathname }, null, pathname
      loadContent pathname
    else
      # Fake a load.
      NProgress.start()
      NProgress.done()

  isSamePathname = (a, b) ->
    a == b || (a == '/' && b == altRootPathname) || (b == '/' && a == altRootPathname)

  loadContent = (pathname) ->
    NProgress.start()

    $.get pathname, (data) ->
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

  # Correct the content upon back.
  $(window).bind 'popstate', (e) ->
    prevState = e.originalEvent.state
    pathname = if prevState then prevState.pathname else altRootPathname
    loadContent pathname

  initCarousel()
  initLightbox()

  NProgress.done()
