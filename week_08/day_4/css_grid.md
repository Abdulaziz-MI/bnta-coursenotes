# CSS Grid

## Learning Objectives:​

* Understand what CSS grid is and how it can be used to organise content in a 2D fashion
* Appreciate the distinction between flexbox and CSS grid
* Be able to align HTML elements vertically and horizontally using CSS Grid
* Understand what is meant by a "fractional unit" (`fr`) and how that is used in CSS Grid properties
* Appreciate the differing use-case of the new `/` syntax in different CSS Grid properties
* Appreciate the differing use-case of the new `span` syntax in different CSS Grid properties
* Appreciate how we can use the `gap` property to create minimum spacing between grid items

## Introduction

You have already seen how flexbox containers can be used to create responsive and clean components for your webpage. Flexbox, however, does have its limitations and you may at some point be in want of a true **two-dimensional** means to organise your webpage content.

![Example of Grid VS Flex](../../../../assets/html_css/css_grid/grid-vs-flexbox.png)

> Souce: [Grid vs Flexbox](https://medium.com/@daniaherrera/getting-started-css-grid-vs-flexbox-b3012fce6476)

This is where CSS Grid comes into play. By first setting up a system of rows and columns, you can then define where each part sits in two dimensions, helping you build more complicated designs.

## CSS Grid

Similar to flexbox, Grid is a very powerful tool for a web developer. When you get into more complex web development you may begin to notice a Grid is usually the foundation of a whole website.

As an example, let's take a look at how the BBC uses a grid on their website.

![Example of Grid VS Flex](../../../../assets/html_css/css_grid/bbc-grid-example.jpeg)

Notice that all of the content on the page is within the outer grid (red boundaries). If you go to the BBC's website and resize your browser window, you will notice all elements will consistently follow this same boundary. That's because **all content on the page is within a Grid**.

Within that main grid, they use multiple inner grids to have a two-dimensional grid layout.

## Adding Grid to our project

Let's get back to our beautiful Duck Appreciation Society website. You're never going to get people to appreciate ducks without a solid grid in there 🦆

If you haven't been following along, you can take a look in the `code_start` directory for the latest code.

If you remember from before, we used flex in the header and contact section, but we haven't managed to align our products section yet. Let's use grid to align this.

Firstly, we need to wrap our three cards in their container in our HTML.

```html
<!-- index.html -->
<div class="products__container">
  <div class="products__card">
    <img src="./assets/duck.jpeg" alt="Image of a duck" />
    <h3>Card Title</h3>
    <p>Card paragraph</p>
    <button>Button</button>
  </div>
  ...
</div>
```

> There should be three cards in this container, we just cut them out for readability. At this point, each card is exactly the same. Notice we changed the card class name from `card` to `products__card`, to be consistent with our naming convention.

Now that we've wrapped our cards in a container, let's put them into a grid using CSS.

```css
/* style.css */
.products__container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-gap: 20px;
}
```

If you open up the browser you'll notice our cards are now nicely aligned in a grid, like magic 🪄.

<img src="https://media.giphy.com/media/l0MYw6Cu1TfY3gsWk/giphy.gif" alt="Magic Gif" height="200">

So let's talk about that CSS we added for a second. The first attribute, `display: grid`, should be pretty straightforward. But the second one might seem a bit confusing.

The `grid-template-columns` attribute lets us tell CSS how many items we want in our grid, and how much space they should take. In this case, we want a grid of three items, spaced evenly. If for example, we wanted the middle item to take up twice the space, we would say `grid-template-columns: 1fr 2fr 1fr;`. Where `fr` stands for a fraction.

The `grid-gap` attribute allows us to add spacing inbetween our grid items. In this case we've added `20px` of space around each grid item.

![CSS Grid display examples](../../../../assets/html_css/css_grid/CSSgrid-grid-templates.png)

We can also specify it in pixels like in the above example.

Let's move on to our footer. If you remember from the wireframe it contains three columns, but if you look at our HTML our footer only contains two elements. So remove the `footer__left` div from our `footer`, and to keep our class naming consistent, change the class `footer__right` to `footer__logo`.

```html
<!-- index.html -->
<footer>
  <nav>
    <a href="#">Home</a>
    <a href="#">About</a>
    <a href="#">Contact</a>
  </nav>
  <div>
    <a href="mailto:ducks@ducksociety.com">ducks@ducksociety.com</a>
    <a href="tel:+44 (5182) 797 9825">+44 (5182) 797 9825</a>
    <p>&copy; Duck Appreciation Society 2022</p>
  </div>
  <div class="footer__logo">
    <img src="./assets/duck.jpeg" alt="Image of a duck" />
  </div>
</footer>
```

Now in the CSS, let's set the `footer` to `display: grid`.

```css
footer {
  background-color: #fdf2d1;
  display: grid;
  grid-template-columns: 1fr 1fr 2fr;
}

.footer__logo {
  text-align: right;
}
```

This time we want the `footer__logo` to take up twice the space as the other two divs. And then we set the `footer__logo` div to align content within it to the right.

And there we have it! We've aligned our footer nicely using Grid. Let's just fix a few alignment issues in our footer.

```css
footer div,
footer nav {
  display: flex;
  flex-direction: column;
}

footer a,
footer p {
  margin: 10px 0;
}
```

Here we are using flex to align the text vertically within the footer nav and footer div. Following this, we set every `a` or `p` tag in the footer to have `margin: 10px 0;`. This means `10px` of spacing on the top and bottom and `0px` on the left and right.

## Additional Grid Resources

### display: grid

**[ grid / inline-grid ]**

[https://developer.mozilla.org/en-US/docs/Learn/CSS/CSS_layout/Grids](https://developer.mozilla.org/en-US/docs/Learn/CSS/CSS_layout/Grids)

As with Flexbox, Grid starts with the parent container. Applying `display: grid` to the parent deems it a _Grid Container_ which will automatically start organising each discrete code block within the container into its row. `display: grid` will create these rows to the width of the parent container whereas `display: inline grid will create the rows at the minimum width required to contain the content of the grid items, as shown below. Note that the red outline exists in the right example to better illustrate the change in dimension.

![CSS Grid display examples](../../../../assets/html_css/css_grid/CSSgrid-display.png)

### Grid Template _(the explicit grid)_

**[ # / fr / repeat(#, #) / minmax(#, #) ]**

The definition of the **explicit grid** and the automatic organisation of the content within it is where Grid shines. With the use of the `grid-template` properties, `grid-template-columns`, `grid-template-rows` and `grid-template-areas`, you can define the spatial organisation of your grid before it is even populated with content. When you later play about with the positioning properties of `grid-column` and `grid-row`, you can even specify columns or rows which do not exist within your Grid template. The placement of these items and the automatic creation of extra grid tracks establish the creation of the **implicit grid**. The combination of the explicit and implicit grids is what is displayed as part of your webpage and mastering the interplay between the two will set you up well as a web developer.

[CSS Tricks - The Difference Between Explicit and Implicit Grids](https://css-tricks.com/difference-explicit-implicit-grids/)

#### grid-template-columns & grid-template-rows

[https://developer.mozilla.org/en-US/docs/Web/CSS/grid-template-columns](https://developer.mozilla.org/en-US/docs/Web/CSS/grid-template-columns)

To define the number and size of the tracks within the **_explicit_** grid we use `grid-template-columns` and `grid-template-rows`. Both properties accept a list of space-separated values defining each track, in order. You can also optionally name the beginning placement of each space by enclosing the name within _[square brackets]_ between the relevant tracks in the template declaration. (_e.g._ `grid-template-columns: [grid_start] 100px [column2-name] 100px [col3-start] 100px`) If the track is not explicitly given a name then it will be assigned one based on its index, **from index 1**.

![CSS Grid display examples](../../../../assets/html_css/css_grid/CSSgrid-grid-templates.png)

There are a few handy pieces of notation which substantially bolster the usability, and robustness, of CSS Grid:

- Fractional units (fr)

  Fractional units are incredibly useful when creating a grid template as they allow you to easily create tracks with sizes equal, or proportional to, one another. They are used in the form: `grid-template-columns: 1fr 2fr 1fr`; with the Grid template assignment properties. Note here that the second column would fill double the space of the first and third columns. When using fractional units alongside static unit declarations, such as those in `px` or `rem`, the ratio of the fractional units defines the distribution of the **remaining free space** within the grid complex.

![CSS Grid repeat and minmax](../../../../assets/html_css/css_grid/CSSgrid-repeat-minmax.png)

- `repeat(#, #)`
  `repeat` is another incredibly useful piece of notation that you can use with Grid. If you have a grid layout which follows a simple, repeating pattern then you do not need to specify the size of each track individually. This is especially useful when using large grid structures. You can even include repeating units as only part of your grid template where you may, for instance, want the outer bounding columns to be of different widths. The repeating unit can also include multiple track declarations within itself. An example of using the `repeat` keyword could be: `grid-template-columns: repeat(10, 1fr)`, where 10 equal-sized columns would be created.
- `minmax(min#, max#)`
  Although there are several similarities, comparing CSS Grid to Flexbox may lead you to believe that Flexbox garners the edge on responsiveness, when this may not be the case. The `minmax` unit is CSS Grid's answer to combined variable screen dimensions and static design elements. As the name would suggest, the `minmax` unit allows us to set both the minimum and maximum sizes for the element, which can vary based on screen size by the inclusion of proportional units (`#%`). An example of a grid where the content has upwards bounds but that scales down past a certain point, could use `minmax`. Combining this with the example above, we could have: `grid-template-columns: repeat(3, minmax(auto, 250px))`. Our three columns would expand until they are at a width of 250px, and remain equal below this point, scaling with screen size demands.

#### grid-template-areas

[https://developer.mozilla.org/en-US/docs/Web/CSS/grid-template-areas](https://developer.mozilla.org/en-US/docs/Web/CSS/grid-template-areas)

Another property which we can use to organise our grid structure before the addition of content is `grid-template-areas`. Through the use of a collection of strings, we can define discrete areas which we can later refer to, to better organise our content without worrying about which column or row to point towards. Each row is defined within a block quote (within a set of `"` quotes), with the area name provided for each cell. An empty cell is denoted with a full-stop `.`. Your area declaration must be rectangular to be valid. Note that the grid lines between regions are automatically generated as _nameOfArea-start_ and _nameOfArea-end_. This means that each gridline may have multiple names, any of which you may use.

![CSS Grid template areas](../../../../assets/html_css/css_grid/CSSgrid-template-areas.png)

```html
<section id="example4_A" class="example">
  <div class="box header">header</div>
  <div class="box primary">primary</div>
  <div class="box secondary">secondary</div>
  <div class="box footer">footer</div>
</section>
```

```css
#example4_A {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr 1fr;
  grid-template-areas:
    "header header header"
    "primary . secondary"
    "footer footer .";
}

#example4_A > header {
  background-color: rgb(47, 79, 79);
  grid-area: header;
}
```

#### grid-template

[https://developer.mozilla.org/en-US/docs/Web/CSS/grid-template](https://developer.mozilla.org/en-US/docs/Web/CSS/grid-template)

A `grid-template` property exists which encompasses the `grid-template-columns`, `grid-template-rows` and `grid-template-areas` properties under one name. The syntax can be fairly difficult to get your head around however so we won't be covering it here in any depth. If you are curious about how the property declaration looks then please find the MDN documentation linked above.

#### grid-auto-column & grid-auto-row

[https://developer.mozilla.org/en-US/docs/Web/CSS/grid-auto-columns](https://developer.mozilla.org/en-US/docs/Web/CSS/grid-auto-columns)

`grid-auto-column` and `grid-auto-row` are two properties which relate to the **implicit grid**. When an item is placed in this implicit grid, either by being positioned out-of-bounds or simply by the grid expanding to accommodate a growing number of items, then this item's track bounds are set by these properties. The `grid-auto-x` properties set the **size** of the implicit grid's columns and rows. Note however that the default value is `auto` which aligns with the minimum width required to accommodate the largest item in the implicit track.

### gap

**[ # / % ]**

[https://developer.mozilla.org/en-US/docs/Web/CSS/gap](https://developer.mozilla.org/en-US/docs/Web/CSS/gap)

The spacing between items in the total grid (implicit and explicit grids) can be defined by the `gap` property. Any positive numerical value is accepted, with percentage values being based on the size of the element (as opposed to the parent). The `gap` property is a combination of the `row-gap` and `column-gap` properties. Note that if only one value is provided, then both properties are set to the provided value.

![CSS Grid gap](../../../../assets/html_css/css_grid/CSSgrid-gap.png)

#### grid-auto-flow

**[ row / column / dense ]**

[https://developer.mozilla.org/en-US/docs/Web/CSS/grid-auto-flow](https://developer.mozilla.org/en-US/docs/Web/CSS/grid-auto-flow)

Now that we have defined the means to set up our grid structure, we can consider how we affect the placement of Grid items within said grid. By default, Grid items will organise themselves into the explicit grid in the order left-right, top-bottom relative to the order in the HTML markup. Providing that each item is of equal size, the resulting grid will be regular and ordered.

There are times, however, when we want something more exciting than a simple array of regularly-sized rectangles. For instance, perhaps we would like some items to span several rows or columns—an action with would disrupt the usual ordering of the grid and likely cause gaps to appear. Applied to the grid container, `grid-auto-flow` can be used to override the default ordering on an **overall** but **by-item** basis:

- `grid-auto-flow: row` is the default value for `grid-auto-flow` and will organise the grid items in a left-right top-bottom fashion. The grid will expand to add extra rows as needed. The ordering relative to the HTML markup will be maintained.

- `grid-auto-flow: column` organises the grid items in top-bottom left-right order. This means that it attempts to fill a column before moving on to the next one. The grid will expand to add extra columns as needed. The ordering relative to the HTML markup will also be maintained here, meaning some gaps can arise with irregularly-spanning items.

- `grid-auto-flow: dense` is a cool property. When there would be a hole left in the grid that a later Grid item could fill, then `grid-auto-flow: dense` will allow that item to break the overall ordering of the grid to fill that gap. This is what we meant by _overall ordered yet by item_ earlier. The below images show an example of this broken ordering for a `grid-auto-flow: row dense` container. Note how Box #4 (highlighted) moved from the end of the second row to fill the space left in Row 1, creating a more complete grid shape. This movement in turn then has a knock-on effect, with the following boxes also changing their position to fill the hole that Box #4 then leaves behind. Note that this process is not perfect, nor is it iterative, and hence can leave gaps when you have many irregularly-shaped items.

![CSS Grid grid-auto-flow row](../../../../assets/html_css/css_grid/CSSgrid-auto-flow-auto.png)

This image shows a generated grid system with varying box sizings and default `grid-auto-flow: row`.

![CSS Grid grid-auto-flow dense](../../../../assets/html_css/css_grid/CSSgrid-auto-flow-dense.png)

This one shows a generated grid system with varying box sizings and `grid-auto-flow: row dense` applied.

### Grid Item Columns, Rows & Areas

**[ # / span ]**

[https://developer.mozilla.org/en-US/docs/Web/CSS/grid-column](https://developer.mozilla.org/en-US/docs/Web/CSS/grid-column)

If you require a bit more control over the position or relative size of your grid items, then there is a selection of properties you can set. Many of these properties make use of a new piece of syntax: `span`. The `span` syntax is handy when you know the exact placement of your grid item(s). Coupled with either a positive integer number or a line name, the `span` syntax can be used to assign the space for a grid item with ease. If a line name is provided as the value following `span` then the grid item grows to span across all necessary tracks until it hits the line specified.

- `grid-column-start` defines the starting column placement for the grid item by specifying the **line name** of where it is to start. The use of the `span` syntax (_e.g._ `grid-column-start: 1 span 2`) may invalidate the need for the following `grid-column-end` property

- `grid-column-end` defines the ending bounds of a grid item by providing the line-name

- `grid-row-start` and `grid-row-end` work the same as above but for rows in place of columns

Similar to other properties we have seen throughout the past documents, these properties can be defined using combined property names to save on code. `grid-column` and `grid-row` work as you would expect them to, taking their two respective properties defined above and placing them under one roof. They use another piece of syntax which we haven't encountered yet, which is the `/` syntax. This is simply a means for separating the values provided and assigning them to the corresponding properties. For instance `grid-column: 2 / 4` breaks down to `grid-column-start: 2` and `grid-column-end: 4`. The `/` syntax is something which is cropping up an increasing amount in currently proposed CSS specifications for new properties, so keep an eye out for these features as they potentially make their way to browsers.

Lastly, `grid-area` does what it says on the tin and simply assigns the grid item to the specified grid-area as defined using `grid-template-areas` or `grid-template`. For an example of how to assign the area, please see the example code in the `grid-template-areas` section above.

## A Note on Properties from Flexbox

**[start / end / center / stretch]**

CSS Grid is in many ways coupled to Flexbox and so the two often work well together—sometimes even sharing property declarations. You can hence loosely consider a `display: grid` container as a `display: flex` container and use the positioning properties outlined in the previous document. Some of the more commonly used properties are outlined below:

- `justify-items` is used to align the grid items along the **inline (row)** axis and hence sets the alignment of each item **within** its grid cell. Individual items can be set using the `justify-self` property. Note that this is **not** the `justify-content` property covered in the Flexbox document.

- `align-items` is used the position the grid items on the **block (cross / column)** axis.

- `place-items` is a combination property which includes both `align-items` and `justify-items`. Again, the `/` syntax is used here to separate the two values provided within one declaration.

It should be noted that `justify-content`, `align-content` and their related `place-content` property do also apply to Grid containers, specifically those where the grid size is less than the size of the grid container. Proper use of the `stretch` value here can be powerful when creating responsive design elements, however, it may be infrequent that you encounter this use case. The CSS Tricks article linked at the top of the document does a great job visually displaying how these properties behave.

## Further Reading

[MDN Documentation - Grid](https://developer.mozilla.org/en-US/docs/Web/CSS/grid)

[CSS Tricks - A Complete Guide to Grid](https://css-tricks.com/snippets/css/complete-guide-grid/)

[Free Code Camp - A Beginner’s Guide to CSS Grid](https://www.freecodecamp.org/news/a-beginners-guide-to-css-grid-3889612c4b35/)

[https://learncssgrid.com/](https://learncssgrid.com/)
