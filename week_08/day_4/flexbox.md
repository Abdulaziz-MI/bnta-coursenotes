# Flexbox

## Learning Objectives:​

* Understand what flexbox is and its use-case, especially in reference to responsive design principles
* Be able to align HTML elements vertically and horizontally using flexbox
* Recognise the different flexbox properties `justify-content` and `align-items`, and their individual use-cases
* Be able to utilise `flex-wrap` to create a pseudo-2D display of like items on a web-page
* Recognise the use-case of flexbox properties relating to sizing such as `flex-basis` and `flex-grow`

## Introduction

Flex containers are marvellous and are used by many web developers to lay out the content of web pages. It allows us to create responsive designs with apparent two-dimensional organisation and has been a cornerstone advancement for web development within the last decade. Understanding how to use flex containers is an essential step toward creating clean and modern websites.

## Background

Back in the dark days of the web, having some sort of horizontal layout on a website was a painful depressing experience 🥲.

Horizontal layouts were possible through the use of `tables` or `float`. The use of the `float` method usually resulted in all sorts of headaches. If you had four elements that you wanted to align horizontally, each element would have to have the value `float: left`, along with the appropriate width to fit within the page.

![Float Left Example](../../../../../assets/html_css/flexbox/float-left.png)

See this article on [All About Floats](https://css-tricks.com/all-about-floats/) for more information.

We're not going to cover this in detail, we just want to emphasize that flex changed the game. So let's get into it.

## Adding flex to our project

There are a few parts of our wireframe that require us to align items horizontally, but later we want it to align vertically when we get to our mobile design.

### Header

Let's start with the header. We have a logo aligned left, navigation aligned to the centre, and nothing aligned right.

Whenever you want to add flex, the very first thing you need to do is tell the `container` element to display its children as flex.

```html
<!-- index.html -->
<header>
  <h1>Duck Appreciation Society</h1>
  <nav>
    <a href="#">Home</a>
    <a href="#">Products</a>
    <a href="#">About</a>
    <a href="#">Contact</a>
  </nav>
</header>
```

If you recall, this is what our header looks like in our HTML. In this case, the `header` element is the container, and `h1` & `nav` are the children. So open up your `style.css` and add the following code into the `header` selector.

```css
/* style.css */
header {
  background-color: #f6e7d5;
  display: flex;
  align-items: center;
}
```

We first added set the display to `flex`, and then added `align-items: center;`. The `align-items` attribute in this case vertically aligns the elements within the `header`. If we changed it to `align-items: flex-start`, you will notice the `nav` will be positioned at the top of the page, but the `h1` element does not change.

If you open up the Inspect tools in your browser and hover your mouse over the `h1` element, you'll notice some orange spacing around it.

![Hovering your mouse over the h1 using inspector tools](../../../../../assets/html_css/flexbox/align-items.png)

Within your website, there have been some default styles added to all of your elements. That's why the links are blue and underlined, and in this case, the `h1` has some spacing on the top and bottom. More specifically, it has a `margin`. So the `align-items` attribute tells any element that has some wiggle room to align itself depending on what you specify.

![Align Items Example](../../../../../assets/html_css/flexbox/flexbox-align-items.png)

You can `align-items` back to `center` and we can continue styling our website. Notice how in the wireframe, the `nav` links are spaced out. In ours, they are quite jumbled together. Let's also make them a flex container.

```css
/* style.css */
h1 {
  width: 25%;
}

header nav {
  width: 60%;
  display: flex;
  justify-content: space-around;
}
```

We've done a few things here:

1. We added a `width` of `25%` to the `h1` element
2. We added a `width` of `60%` to the `nav` element
3. We set the `nav` to display `flex`, and told it to justify its content to `space-around`.

Firstly, let's talk about the widths. If you notice in the wireframe, we have some space on the right-hand side of our header, so we added a custom width of `25%` to the `h1`, and `60%` to the `nav`. A percentage value is always out of 100, so that would mean we have 15% of space left over, which now appears on the right-hand side.

Secondly, let's discuss this `justify-content` attribute. This is useful to space things in different ways. We've created some examples below to give you an idea:

![Justify Content Basics](../../../../../assets/html_css/flexbox/flexbox-justify-content-basics.png)

![Justify Content Continued](../../../../../assets/html_css/flexbox/flexbox-justify-content-cont.png)

Now our header is starting to look a bit more like our wireframe! Let's do the same in the contact section.

### Contact

Our contact section is nothing too crazy, we just need to align our text and form side-by-side, and align our form vertically.

```css
/* style.css */
.contact {
  background-color: #e0eaf5;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.contact div {
  width: 50%;
}

form {
  display: flex;
  flex-direction: column;
}
```

Nothing new here, we've used all of these properties before. We set the `section` with the class name of `contact` to display flex, justify its content around and vertically align its children in the centre. We also set the width of each div within the `contact` element to be `50%`. This ensures they are evenly distributed.

Then we also set the form to display flex, and we set its `flex-direction` to `column`. This will stack the form elements vertically, which matches our wireframe.

You'll notice we didn't touch the horizontal layout of our products or our footer. The reason for this is these would be more suited to a grid layout, which we will cover in the next lesson.

This is a very brief overview of how powerful flex is, but it goes a lot deeper. We've left some additional content if you want to go into further detail in it below.

## Additional Flex Properties

### flex-wrap

**[ nowrap / wrap / wrap-reverse ]**

[https://developer.mozilla.org/en-US/docs/Web/CSS/flex-wrap](https://developer.mozilla.org/en-US/docs/Web/CSS/flex-wrap)

`flex-wrap` is an area where Flexbox particularly shines. Responsive design, the requirement for a single design to function and look good regardless of device, and therefore screen size, was once a matter of JavaScript over HTML and CSS. Now, with flex containers and `flex-wrap`, it is easy to collect together your content to give the impression of a two-dimensional grid which rearranges with changing screen size. Further, it is easy to ensure that your flex items are of the same size, making the entire grid cohesive and clean.

![Flex wrap examples](../../../../assets/html_css/flexbox/flexbox-wrap.png)

- [5A]`flex-wrap: nowrap` is the default value for flexboxes. `nowrap` means that flex items do not wrap onto another row or column with shrinking content dimensions. This causes the items to either shrink in size if the item has no strict dimensions or to overflow the container if they do.

- [5B] `flex-wrap: wrap` causes the flex items to create and populate a new row or column (depending on `flex-direction`) at the onset of where the content would begin shrinking or overflowing. The order of the items which flow to the next container follows standard Western reading order: left-to-right, top-to-bottom.

- [5C] `flex-wrap: wrap-reverse` is akin to `wrap` where the content is allowed to create further rows or columns to maintain item sizing and bounds. The order of the items however is different and follows left-to-right, bottom-to-top, where the last item in the HTML markup is situated top-right instead of bottom-right. Combining this with `flex-direction: row-reverse` creates a reversed timeline for your HTML markup.

It should be noted that while wrapping flex items _can_ give the impression of two-dimensional organisation of content, it is not, and is inherently **one-dimensional**. This causes a little issue for many responsive, modern designs until vertical _and_ horizontal alignment are present in the design. An example where you may encounter this is if the design calls for a true grid-style component with a varying but strict number of columns with changing screen size. The nuance is difficult to communicate through text, however, just know that this is when you should look at using CSS Grid—when you need a truly two-dimensional component layout.

### flex-grow

**[ # ]**

[https://developer.mozilla.org/en-US/docs/Web/CSS/flex-grow](https://developer.mozilla.org/en-US/docs/Web/CSS/flex-grow)

The free space within a flex container is distributed between all flex-items which do not have defined dimensions. `flex-grow` sets the flex-grow factor of a flex-item's main size and hence the claim of each item on this free space. It is applied to each **flex-item** and not the parent. The default value is `flex-grow: 0` and any positive number value is accepted. You can consider it as a weighting factor in the division of free space within a flex container between the flex-items. This means that an item with `flex-grow: 2` gains twice the amount of growth-space proportionally to an item with `flex-grow: 1`. The `flex-grow` values hence form a ratio proportional to the distribution of free space. Note that to better use `flex-grow`, it is best to ensure that your flex container has strict dimensions set which are greater than the accumulation of the flex items' sizings. If you afford yourself extra space between your items, then it is far easier to assign the free space in a meaningful way and create cool effects.

![Flex grow examples](../../../../assets/html_css/flexbox/flexbox-flex-grow.png)

### flex-shrink

**[ # ]**

[https://developer.mozilla.org/en-US/docs/Web/CSS/flex-shrink](https://developer.mozilla.org/en-US/docs/Web/CSS/flex-shrink)

`flex-shrink` is the counterpart to `flex-grow` and hence defines the amount by which a flex-item shrinks, to satisfy the container, if the additive size of all flex items is greater than the container's bounds. The default value is `flex-shrink: 1` and any positive number value is accepted. Same as `flex-grow`, this property is set on the flex items themselves. This default means that flex-items will automatically shrink to satisfy their container's bounds. Again, `flex-shrink` defines a ratio of space which each item fills within a container, however relate to the _proportional decrease_, not the definite sizings. A flex item with `flex-shrink: 2` hence **shrinks by double** the size of an item with `flex-shrink: 1` when changing size to fit within a flex container. Note that the item with `flex-shrink: 2` is hence **_not half the size_** of that with `flex-shrink: 1`, rather, it has **_shrunk by double the amount_**.

![Flex shrink examples](../../../../assets/html_css/flexbox/flexbox-flex-shrink.png)

### flex-basis

**[ # / % / content ]**

[https://developer.mozilla.org/en-US/docs/Web/CSS/flex-basis](https://developer.mozilla.org/en-US/docs/Web/CSS/flex-basis)

`flex-basis` is the property used to set the initial main size of a flex item. This can be set as either an absolute value, a percentage value of the parent flex container, `content` (sets the size to the minimum space needed to container the flex items' content), or `auto` (defers to the intrinsic size of the flex item). The default value is `auto`. This property is especially useful when defining strict growth and shrink relationships for your flex items.

### flex

[https://developer.mozilla.org/en-US/docs/Web/CSS/flex](https://developer.mozilla.org/en-US/docs/Web/CSS/flex)

Another common flexbox property exists which is simply `flex`. It contains all three of the previous properties of `flex-grow`, `flex-shrink` and `flex-basis` into one shortened form. The property accepts the three in the order `grow`, `shrink`, `basis`, _e.g._ `flex: 1 1 220px`.

### gap

**[ # / % ]**

[https://developer.mozilla.org/en-US/docs/Web/CSS/gap](https://developer.mozilla.org/en-US/docs/Web/CSS/gap)

`gap` is another of these shorthand properties which combines `row-gap` and `column-gap`, two properties which were originally implemented as part of CSS Grid before being added to Flexbox. As expected, it accepts two absolute or proportional values which define the `row-gap` and `column-gap` properties respectively. If only one value is provided then both values will be set to the provided value. It should be noted that percentage values here are proportional to the size of the _flex-item_ and _not the container_. Unlike other Flexbox properties where values exist with respect to the main-axis of the flex container, the _row_ and _column_ denominations are always with respect to the plane of the page.

![Flex gap examples](../../../../assets/html_css/flexbox/flexbox-gap.png)

## Further Reading

[MDN Documentation - Flexbox](https://developer.mozilla.org/en-US/docs/Learn/CSS/CSS_layout/Flexbox)

[MDN Documentation - Flex](https://developer.mozilla.org/en-US/docs/Web/CSS/flex)

[CSS Tricks - A Complete Guide to FlexBox](https://css-tricks.com/snippets/css/a-guide-to-flexbox/)

[FreeCodeCamp - Learn CSS Flexbox in 5 minutes](https://www.freecodecamp.org/news/learn-css-flexbox-in-5-minutes-b941f0affc34/)

[All about Floats](https://css-tricks.com/all-about-floats/)
