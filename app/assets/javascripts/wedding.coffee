define ['jquery'], () ->

  carouselParams = (defaultParams) ->
    defaultParams.startSlideshow = true

    $(defaultParams.container).hover ->
      $(this).children('.prev').css 'display', 'inline'
      $(this).children('.next').css 'display', 'inline'
      $(this).children('.play-pause').css 'display', 'inline'
      $(this).children('.indicator').css 'display', 'inline'
    , ->
      $(this).children('.prev').css 'display', 'none'
      $(this).children('.next').css 'display', 'none'
      $(this).children('.play-pause').css 'display', 'none'
      $(this).children('.indicator').css 'display', 'none'

    defaultParams

  {
    carouselParams: carouselParams
  }
