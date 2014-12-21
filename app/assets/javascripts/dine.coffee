define ['jquery'], () ->

  initCarousel = (carouselLinks, container) ->
    blueimp.Gallery carouselLinks, {
      container: container,
      carousel: true,
      startSlideshow: false
    }

  {
    initCarousel: initCarousel
  }
