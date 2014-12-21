define [], () ->

  initCarousel = (carouselLinks, container) ->
    blueimp.Gallery carouselLinks, {
      container: container,
      carousel: true,
      stretchImages: false,
      startSlideshow: false
    }

  {
    initCarousel: initCarousel
  }
