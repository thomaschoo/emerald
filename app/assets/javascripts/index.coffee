define ['jquery', 'nprogress'], () ->

  NProgress.configure { showSpinner: false }
  NProgress.start()

  altRootPathName = '/home'

  $('li.menu-item').on 'click', () ->
    pathName = '/' + $(this).data 'url'
    currentPathName = window.location.pathname
    if !isSamePathName currentPathName, pathName
      window.history.pushState { pathName: pathName }, null, pathName
      loadContent pathName
    else
      # Fake a load.
      NProgress.start()
      NProgress.done()

  isSamePathName = (a, b) ->
    a == b || (a == '/' && b == altRootPathName) || (b == '/' && a == altRootPathName)

  loadContent = (pathName) ->
    NProgress.start()

    $.get pathName, (data) ->
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
    pathName = if prevState then prevState.pathName else altRootPathName
    loadContent pathName

  initCarousel()
  initLightbox()

  NProgress.done()
