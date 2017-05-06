<?php
/**
 * The template for displaying the footer
 *
 * Contains footer content and the closing of the #main and #page div elements.
 *
 * @package WordPress
 * @subpackage Twenty_Twelve
 * @since Twenty Twelve 1.0
 */
?>
	</div><!-- #main .wrapper -->
	<footer id="colophon" role="contentinfo">
            <div id="site-info" class="site-info">
                <span class="footer-title"><a href="<?php echo esc_url(home_url('/')); ?>"><?php bloginfo('name'); ?></a></span>
                <?php printf(__('%s', 'white-spektrum'), 'Powered By: '); ?><a href="<?php echo esc_url('https://wordpress.org'); ?>"><?php printf(__('%s', 'white-spektrum'), 'WordPress'); ?></a>
            </div>
	</footer><!-- #colophon -->
</div><!-- #page -->

<?php wp_footer(); ?>
</body>
</html>