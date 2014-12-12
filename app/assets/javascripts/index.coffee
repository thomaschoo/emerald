$ ->

    $('li.menu-item').hover ->
        $(this).css 'cursor', 'pointer'
        $div = $(this).children 'div'
        $div.css 'color', '#800'
        $div.children('img').css 'visibility', 'visible'
    , ->
        $div = $(this).children 'div'
        $div.css 'color', '#273809'
        $div.children('img').css 'visibility', 'hidden'

    $('li.menu-item').on 'click', () ->
        url = '/' + $(this).data 'url'
        $.get url, (data) ->
            $('#content').html data
            window.scrollTo 0, 0

