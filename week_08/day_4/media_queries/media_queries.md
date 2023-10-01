# Media Queries

## Learning Objectives:​

* Understand what a media query is and how this relates to explicit responsive behaviour
* Appreciate the purpose behind including explicit responsive behaviour
* Be able to add a media query to a webpage to make it responsive to some browser trait such as screen-width

## Introduction

Media queries are a powerful tool within CSS that allows us to have CSS based on screen width. This allows us to modify our website for different screen sizes, making sure it looks great regardless of whether you're on a mobile or a desktop screen.

Media queries are wrappers to other CSS rule sets. Essentially, you provide some context to a set of CSS rules, defining when they apply. This looks like below:

```css
@media screen and (max-width: 500px) {
  body {
    background-color: pink;
  }
}
```

The above example is a media query which applies the `background-color: pink` property to content with the `.className` class when the screen is smaller than 500px wide. 

## Adding Media Queries to our project
Let's add Media queries to our project to make sure it looks good on different devices.

If you remember from our wireframing lesson, we built a wireframe for mobile devices too. So we need to change any horizontally aligned items to stack vertically on smaller devices.

Let's start with tablet devices. Open up your `style.css` and we're going to add in a media query. As always, we have provided the `code_start` file if you haven't been following along.

```css
/* style.css */
@media screen and (max-width: 900px) {
  .products__container {
    grid-template-columns: 1fr 1fr;
  }
  header nav {
    width: 75%;
  }
}
```

Here we have set our `products__container` to have 2 items horizontally when the screen is smaller than 900px. This will cater for tablets, but let's make another one to cater for mobile devices.

```css
/* style.css */
@media screen and (max-width: 600px) {
  .products__container, footer {
    grid-template-columns: 1fr;
  }
  header nav {
    display: none;
  }
  .contact {
    flex-direction: column;
  }
  .contact div {
    width: 100%;
  }
}
```
We've done a few things here, but most of it should be somewhat familiar at this point. Firstly, these changes only take place when the screen is **smaller** than `600px`. We set the `products__container` and the `footer` grids to only contain one element per column, therefore it stacks vertically.

We also hide the header navigation, by setting its `display` to `none`. According to our wireframe, we will have a hamburger icon there instead.

Then we set the `flex-direction` of our contact section to `column`, which also stacks it vertically.

And finally, we set the `width` of our contact section children back to `100%`, because it is stacked vertically here.

Et voilà 🥖🇫🇷! Our website now responds to the screen size, adjusting itself to utilise screen space on all sizes! Pretty neat right? 

<img src="https://media.giphy.com/media/s3d5ugcxFDApG/giphy.gif" alt="Dwight Gif" height="300">

## (Optional) Final touches

Okay okay I know, our website still isn't done. We need to add some polish to make it look a little prettier.

Firstly, we never added a `hero` section, so let's add an image for that. We've added a new photo named `duck-hero.jpeg` to the `assets` folder in `code_start`. But we're not going to add it in HTML. Instead, we will add it in CSS using the background property.

```css
/* style.css */
body {
  margin: 0;
}

section {
  min-height: 500px;
}

.hero {
    background-image: url(./assets/duck-hero.jpeg);
    background-position: center;
    background-size: cover;
}
```

Firstly, we remove the default margin spacing from the body. Then we set a minimum height for each section of `500px`. This adds a bit of breathing room between sections.

Then within our hero section, we set the entire section's background to be an image. We position that image in the centre and we set the `background-size` to `cover`. This means CSS will resize the image to fit within our div.

Now let's look at some spacing issues. If you refer back to the wireframe, you'll notice there is a bit of space on the left and right of each section, but on our website, we don't have any spacing on the left and right.

```css
/* style.css */
header, section, footer {
  padding: 20px 10%;
}
```

Here we instructed CSS to add `20px` of space vertically (top and bottom) to every header, section and footer, and add `10%` of space horizontally (left and right).

We have just a few little things to tweak and we can call our website complete. Firstly, buttons, fonts and form inputs need some sprucing up.

```css
/* style.css */
body {
  margin: 0;
  font-family: sans-serif;
}

a {
  text-decoration: none;
  color: #4b73c1;
  font-weight: bold;
  font-size: 18px;
}

input {
  padding: 10px;
  max-width: 300px;
  margin: 10px;
}

button, input[type="submit"] {
  background-color: #4C73C1;
  padding: 10px;
  width: 100px;
  color: #fff;
  text-transform: uppercase;
  font-weight: bold;
  border: 5px solid #3C5370;
}

.contact__text {
  width: 60%;
}
.contact__form {
  width: 40%;
}
```

There's a lot of code here, but it's nothing too crazy. First, we set the `body` to have a `font-family` of `sans-serif`. This changes all text within the body to have a smoother font than the default `serif`.

Next, we add some styling to our links, we choose the same colour as our buttons in the wireframe, and just made them a little bigger and bolder.

On our regular input fields, we added some padding to give them some nice space and set a max width so they aren't too long.

For our buttons (and any input with the `type` of `submit`) to have a few modifications to match the wireframe a bit more. This was done by adding a background colour, giving it some space and a border that is `5px` thick.

Finally, we just made some small width changes to the `div`'s in the contact section. 

And that's it! I know our website doesn't match the wireframe exactly, but it's definitely a good effort. Watch out world, here comes the duck appreciation society.

<img src="https://media.giphy.com/media/lvHir7Z9eCCbqm98IY/giphy.gif" alt="Watch out gif" height="300">
