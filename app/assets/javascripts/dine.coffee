define [], () ->

  initCarousel = (carouselLinks, container) ->
    blueimp.Gallery carouselLinks, {
      container: container,
      carousel: true,
      stretchImages: 'cover',
      startSlideshow: false
    }

  {
    initCarousel: initCarousel
  }
