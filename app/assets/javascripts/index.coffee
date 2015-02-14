define ['jquery', 'nprogress', 'blueimp'], ($, np, blueimp) ->

  NProgress.configure { showSpinner: false }
  NProgress.start()

  altRootPathname = '/home'

  $('.burger').on 'click touchstart', (e) ->
    e.stopPropagation()
    $('#menu')
      .addClass 'open'
      .find('li').removeClass 'active'

    pathname = location.pathname.substring 1
    if pathname == ''
      pathname = altRootPathname.substring 1

    $('span.icon-' + pathname).parents('li').addClass 'active'

  $('body').on 'click touchstart', () ->
    closeMenu()

  closeMenu = () ->
    $menu = $('#menu')
    if $menu.hasClass 'open'
     $menu.removeClass 'open'

  $('#menu').on 'click touchstart', (e) ->
    e.stopPropagation()

  $('li.menu-item').on 'click', () ->
    pathname = $(this).data 'pathname'
    currentPathname = window.location.pathname
    if !isSamePathname currentPathname, pathname
      window.history.pushState { pathname: pathname }, null, pathname
      loadContent pathname
    else
      # Fake a load.
      NProgress.start()
      window.scrollTo 0, 0
      NProgress.done()

    closeMenu()

  isSamePathname = (a, b) ->
    a == b || (a == '/' && b == altRootPathname) || (b == '/' && a == altRootPathname)

  loadContent = (pathname) ->
    NProgress.start()

    $.get pathname, (data) ->
      $('#main-content').html data
      window.scrollTo 0, 0

      initCarousel()
      initLightbox()
      initMap()

      NProgress.done()

  # Initialize the carousel, if applicable.
  initCarousel = () ->
    carouselLinks = $('#links a')

    if carouselLinks.length > 0
      blueimp carouselLinks, {
        container: '#carousel'
        carousel: true
        stretchImages: 'cover'
        startSlideshow: false
        onopened: () ->
          $('.prev').css 'display', 'inline'
          $('.next').css 'display', 'inline'
          $('.indicator').css 'display', 'inline'
      }

  # Initialize the lightbox, if applicable.
  initLightbox = () ->
    $('#lightbox-links').on 'click', (event) ->
      event = event || window.event
      target = event.target || event.srcElement
      link = if target.src then target.parentNode else target
      links = $(this).children 'a'
      blueimp links, {
        container: '#carousel'
        index: link
        event: event
        slideshowInterval: 3000
      }

  # Initialize the map, if applicable.
  initMap = () ->
    if $('#map').length > 0
      controller = window.location.pathname.replace '/', ''
      require [controller], (controller) ->
        controller.initMap()

  # Correct the content upon back.
  $(window).bind 'popstate', (e) ->
    prevState = e.originalEvent.state
    pathname = if prevState then prevState.pathname else altRootPathname
    loadContent pathname

  initCarousel()
  initLightbox()
  initMap()

  NProgress.done()
