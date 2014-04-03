Table primary = new Table(game.skin);
primary.debug();
primary.setFillParent(true);
primary.center();


TextButton back = new TextButton("Back", game.skin);
TextButton apply = new TextButton("Apply Settings", game.skin);

/* Checkboxes */
CheckBox intro = new CheckBox("Skip the Game Intro", game.skin);
CheckBox music = new CheckBox("Play Background Music", game.skin);
CheckBox fullscreen = new CheckBox("Use Fullscreen (Experimental)", game.skin);


/* Header */
Label title = new Label("Game Settings", game.skin);
title.setFontScale(2f);

/* Add actors to table */
primary.add(title).row();

// Table for content
Table tableContent = new Table(game.skin);
tableContent.add(intro).left().row();
tableContent.add(music).left().row();
tableContent.add(fullscreen).left().row();

primary.add(tableContent).pad(20).row();

// Table for buttons
Table tableButtons = new Table(game.skin);
tableButtons.add(back).width(100f).height(45f);
tableButtons.add(apply).height(45f);
primary.add(tableButtons);

getStage().addActor(primary);