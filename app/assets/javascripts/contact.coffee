define [], () ->

  initMap = () ->
    require ['async!http://maps.google.com/maps/api/js?sensor=true'], () ->
      require ['gmaps'], () ->
        lat = 43.605628
        lng = -79.65281

        map = new GMaps {
          el: '#map'
          lat: lat
          lng: lng
          zoom: 16
          zoomControlOptions: {
            style: google.maps.ZoomControlStyle.SMALL
          }
        }

        map.addMarker {
          lat: lat
          lng: lng
          clickable: false
        }

        map.addControl {
          position: 'top_right'
          content: """
            <div title='#{Messages('contact.gmaps.directions.tooltip')}'>
              #{Messages('contact.gmaps.directions.title')}
            </div>
          """
          classes: 'control'
          events: {
            click: () ->
              win = window.open Messages("contact.gmaps.url"), '_blank'
              win.focus()
            mouseover: () ->
              $(this).addClass 'control-hover'
            mouseout: () ->
              $(this).removeClass 'control-hover'
          }
        }

  {
    initMap: initMap
  }
