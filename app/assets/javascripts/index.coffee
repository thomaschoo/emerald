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
        NProgress.start()
        $.get url, (data) ->
            $('#main-content').html data
            NProgress.done()
            window.scrollTo 0, 0

            initGallery()

    # Add the gallery carousel, if applicable.
    initGallery = () ->
        galleryLinks = $('#links a')

        if galleryLinks.length > 0
            blueimp.Gallery galleryLinks, {
                container: '#blueimp-gallery-carousel',
                carousel: true,
                stretchImages: 'cover'
            }

            $('#blueimp-gallery-carousel').hover ->
                $(this).children('.prev').css 'display', 'inline'
                $(this).children('.next').css 'display', 'inline'
                $(this).children('.play-pause').css 'display', 'inline'
                $(this).children('.indicator').css 'display', 'inline'
            , ->
                $(this).children('.prev').css 'display', 'none'
                $(this).children('.next').css 'display', 'none'
                $(this).children('.play-pause').css 'display', 'none'
                $(this).children('.indicator').css 'display', 'none'

    initGallery()
    NProgress.configure { showSpinner: false }

