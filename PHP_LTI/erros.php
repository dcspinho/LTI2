<?php  if (count($erros) > 0) : ?>
  <div class="error">
  	<?php foreach ($erros as $error) : ?>
  	  <p><?php echo $error ?></p>
  	<?php endforeach ?>
  </div>
<?php  endif ?>