<?php
/*
================================================================================================
Silver Platinum - functions.php
================================================================================================
This is the most generic template file in a WordPress theme and is one of the two required files 
for a theme (the other being template-tags.php). This file is used to maintain the main functionality 
and features for this theme. The second file is the template-tags.php that contains the extra functions 
and features.

@package        Silver Platinum WordPress Theme
@copyright      Copyright (C) 2014. Benjamin Lu
@license        GNU General Public License v2 or later (http://www.gnu.org/licenses/gpl-2.0.html)
@author         Benjamin Lu (https://www.lumiathemes.com/)
================================================================================================
*/

/*
================================================================================================
Table of Content
================================================================================================
 1.0 - Enqueue Styles and Scripts
 2.0 - Theme Setup
================================================================================================
*/

/*
================================================================================================
 1.0 - Enqueue Styles and Scripts
================================================================================================
*/
function silver_platinum_enqueue_scripts() {
    // Parent Theme and Child Theme Stylesheet for Silver Platinum.
    $parent_style = 'twentytwelve-style';
    $child_style = 'silver-platinum-style';
    
    // Load Twenty Twelve and Silver Platinum Stylesheet for Silver Platinum.
    wp_enqueue_style($parent_style, get_template_directory_uri() . '/style.css');
    wp_enqueue_style($child_style, get_stylesheet_directory_uri() . '/style.css');
    
    // Enable and activate Font Awesome for Silver Platinum.
    wp_enqueue_style('font-awesome', get_stylesheet_directory_uri() . '/extras/font-awesome/css/font-awesome.css', '20160601', true);

   // Enable and active Google Fonts (Sanchez and Merriweather Sans) Locally for Silver Platinum.
    wp_enqueue_style('silver-platinum-local-fonts', get_stylesheet_directory_uri() . '/extras/fonts/custom-fonts.css', '11162016', true);
}
add_action('wp_enqueue_scripts', 'silver_platinum_enqueue_scripts');

/*
================================================================================================
 2.0 - Theme Setup
================================================================================================
*/


function silver_platinum_entry_posted_on() {
    printf(('Published <b>%2$s</b> / by <b>%3$s</b>'), 'meta-prep meta-prep-author', 
    sprintf('<a href="%1$s" title="%2$s" rel="bookmark"><span class="entry-date">%3$s</span></a>',
        esc_url(get_permalink()),
        esc_attr(get_the_time()),
        get_the_date(get_option('date_format'))),
    sprintf('<a href="%1$s" title="%2$s">%3$s</a>',
    esc_url(get_author_posts_url(get_the_author_meta('ID'))),
    esc_attr(sprintf(__('View all posts by %s', 'white-spektrum'), get_the_author())), 
    get_the_author()
    ));

    if ( !is_page() && !post_password_required() && (comments_open() || get_comments_number())) {
        echo ' / <span class="comments-link"><b>';
            comments_popup_link( sprintf( __( 'Leave a Comment', 'white-spektrum')));
        echo '</b></span>';
    }
}


function silver_platinum_entry_taxonomies() {
    $cat_list = get_the_category_list(__(' | ', 'silver-platinum'));
    $tag_list = get_the_tag_list('', __(' | ', 'silver-platinum'));

    if ($cat_list) {
        printf('<div class="cat-link"> %1$s <span class="cat-list"l><b><i>%2$s</i></b></span></div>',
        __('<i class="fa fa-folder-open-o"></i> Posted In', 'silver-platinum'),  
        $cat_list
        );
    }

    if ($tag_list) {
        printf('<div class="tag-link">%1$s <span class="tag-list"><b><i>%2$s</i></b></span></div>',
        __('<i class="fa fa-tags"></i> Tagged', 'silver-platinum'),  
        $tag_list 
        );
    }
}