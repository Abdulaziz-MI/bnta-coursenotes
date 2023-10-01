# CSS Selectors

## Learning Objectives:​

* Understand what is meant by a "CSS Selector"
* Be able to select different HTML elements for a CSS rule set via class, id and element name

## What is a CSS Selector?

We've spent all this time in HTML building the structure of our website. We essentially laid down some good foundations to work with, but now we need to add style ✨.

Of course, we do this using CSS, but we need to get more specific. We need to tell CSS which elements go where, and what they should look like.

For this, we need to know how to select elements appropriately. From the last lesson, you probably noticed we added class names to some elements. The reason for this is we thought it would be useful when we came to selecting them easily in CSS.

There are different ways to select different items in CSS, so let's cover the basics and we will leave some extra material below if you want to go into more advanced detail.

## Basic types of selectors

`element` - Selects the specified element. E.g.

```css
section {
  background-color: red;
}
```

> This tells CSS to "look for every section element in the HTML, and make its background red".

`.class` - Selects an element by it's class name E.g.

```css
.products {
  color: blue;
}
```

> Tells CSS to "look for any element with the class name `products`, and make any text within it blue".

`#id` - Selects an element by its id E.g.

```css
#nav {
  font-weight: bold;
}
```

> Tells CSS to "look for any element with the id `nav`, and make any text within it bold".

We can also target elements within a certain element. For example:

```html
<nav>
  <a href="#">Home</a>
  <a href="#">About</a>
  <a href="#">Contact</a>
</nav>
<a href="#">Another link</a>
```

If we wanted to modify _only_ the `a` tags _within_ the `nav`, we could do the following:

```css
nav a {
  font-style: italic;
}
```

> Tells CSS to find any `a` tag inside of a `nav` tag, and make it italic. The last `a` tag would not be affected because it is not inside the `nav`.

## Picking up where we left off

Let's keep going with the code we have been working with over the last few lessons. If you have lost your spot, you can take the `code_start` code and work from there.

We've built up the foundations of our website using HTML, and now we want to start working on styling it. If you recall, our wireframe looks like this:

![Desktop Wireframe](../../../../../assets/html_css/wireframing/wireframe_desktop.png)

However, if you open up the website we've built so far, it doesn't look anything like that.

Let's start by adding the colours to each section as we have in our wireframe, using what we have learnt from selectors.

```css
/* style.css */
header {
  background-color: #f6e7d5;
}

.products {
  background-color: #e5efdb;
}

.contact {
  background-color: #e0eaf5;
}

footer {
  background-color: #fdf2d1;
}
```

If you recall from earlier, with the header and footer we can select them by their element name, so we don't need a class name here. But the products and contact elements are both a section, so we needed to select them individually. Therefore we added a class name to each and select them by said class name.

We also should resize some of these images. If you chose the same image that we are using, you'll notice it takes up way too much space.

```css
/* style.css */
.card img {
  width: 300px;
}
```

If you remember, here we are telling CSS to select any `img` element _inside_ of an element with the class name of `card`. If you hover your mouse over the CSS code in Visual Studio Code, it will give you an idea of what you are selecting:

![Hovering your mouse over an element in CSS](../../../../../assets/html_css/css_selectors/css_select_hover.png)

So if you are ever unsure, you can always hover your mouse over a CSS element to get an idea.

You'll notice that after you add that code, our card images are resized nicely, but our footer image is still huge. So let's update that image too.

```css
footer img {
  width: 150px;
}
```

Now our website is starting to take some shape! There's a lot more information on CSS selectors, so if you'd like to know more you can check [Further Reading](#further-reading) below.

# Further Reading

There are more selectors than you will find in the following lists, however, these are the selector types you will likely most commonly use. Follow the links below if you wish to view a complete guide.

[MDN - CSS Selectors](https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Selectors)

[w3schools - CSS Selector Reference](https://www.w3schools.com/cssref/css_selectors.asp)

### Selectors

- `*` (wildcard)

  The wildcard selector can be used to select **_everything_** within a document. It is commonly used within a reset block.

- `element`

  The element selector is used to select **_all_** elements of the specified type (_e.g._ p, div, section). Used most commonly to select all elements of one type to apply global styling (_e.g._ targeting all h1 elements to apply an underline)

- `.class`

  The class selector is used to select **_all_** elements with the specified class attached. This is the most common selector type you will use, especially at the beginning of your web dev career if you follow the guidance above.

- `#id`

  The ID selector is used to select the **_single_** element with the specified ID. Remember that IDs are unique and thus single-use, unlike classes. If you try to apply an `id` to multiple elements then the styling will not be applied to either the latter or both elements. Use this when you have an element which is unique within your site

- `[attribute]`

  The attribute selector is used to select all HTML elements which hold the specified attribute. An example of this may be when you have a retractable menu component to which you assign the `data-active` attribute, to track whether the menu is to be visible or not

- `[attribute=value]`

  By specifying the value of the attribute, you can gain further control over what elements you are selecting. This is again incredibly useful for when you are using custom HTML attributes. You could, using the same example as above, make your retractable menu switch between `visibility: hidden` and `visibility: visible` by selecting only when `data-active="true"`—a key detail when coding for accessibility

### Combinations

- `element.class`

  This combination of selectors targets all elements of the type specified which also house the class specified

- `element,element`

  Two selectors of any type adjoined by a comma selects **_all of both_** types specified

- `.class1.class2`

  The combined class selector type is used to select an element which has **_both_** classes applied

- `.class1 .class2`

  Stating two (or more) classes brings structural considerations to your selection. This example selects all elements with the `class="class2"` which are descendants of an element with `class="class1"`

- `element1 > element2`

  The direct descendant selector allows you greater fidelity for what elements you are selecting. It will allow you to select only the elements `element2` which are direct descendants of `element1`

- `element1 + element2`

  The addition symbol is the _adjacent sibling combinator_ and is used to select the elements of type `element2` which occur directly after each of type `element1`, on the same level

### Pseudo-classes

[MDN - Pseudo-Classes and Pseudo-Elements](https://developer.mozilla.org/en-US/docs/Learn/CSS/Building_blocks/Selectors/Pseudo-classes_and_pseudo-elements)

[https://developer.mozilla.org/en-US/docs/Web/CSS/Pseudo-classes](https://developer.mozilla.org/en-US/docs/Web/CSS/Pseudo-classes)

All CSS pseudo-classes start with a single colon `:`. You can think of these as **context selectors** which select a special state of the specified element. (_e.g._ `a:visited` selects all anchor elements which have previously been visited by the user agent)

- `:hover`

  Activates when the item is targeted by the mouse

- `:focus`

  Activates when the user-agent targets the element (usually achieved by `[tab]`-ing to the element or after being activated)

- `:focus-within`

  Activates as `:focus` for the parent element along with any descendants which have `:focus` stylings applied

- `:active`

  Activates when the element has been activated by the user agent (_e.g._ is being clicked on)

- `:link`

  Activates if the associated link **has not** been visited previously

- `:visited`

  Activates if the associated link **has** been visited previously

- `:root`

  Targets the element that is at the root of the webpage document (_e.g._ the starting `html` tag). Can be used to select the entire document to set up default stylings

- `:nth-child(#)`

  Selects the n-th child of an element in index `#`. Formulae can be used to select a pattern of children elements (_e.g._ `2n` can be used to select every even-indexed child)

- `:first-child`

  Selects the first child of an element

- `:last-child`

  Selects the last child of an element

There are more pseudo-classes than in the list above, we have simply highlighted those which we believe would be most useful to you. A group of pseudo-classes which I have not listed above but that may be worth exploring all relate to the `form` element. The MDN documentation linked above is a great resource to view an exhaustive list of pseudo-classes.

### Pseudo-elements

[https://developer.mozilla.org/en-US/docs/Web/CSS/Pseudo-elements](https://developer.mozilla.org/en-US/docs/Web/CSS/Pseudo-elements)

All CSS pseudo-elements start with a double colon `::` and represent a specific region **within an established element**. They act as if they have added a new HTML element to your markup. There are around 16 pseudo-elements listed in the MDN documentation, many of which are experimental. Each has its niche use, and some may find themselves at home within certain designs, however, we are only going to cover two of the most important pseudo-elements here: `::before` and `::after`

- `::before`

  As you may be able to guess, the `::before` pseudo-element selects the region **just before** the associated element. This can be incredibly powerful for _e.g._ assigning regions which grow to fill the space provided, hence positioning the element a specific way, or; for applying a background colour or image which are later unaffected by an applied `filter` property

- `::after`

  Reflection of the `::before` element, the `::after` pseudo-element selects the region immediately following the specified element

Pseudo-elements are incredibly powerful when used correctly, and can often help tidy up your markup. Their use-case, however, isn't always the most obvious. The below CSS Tricks article outlines seven ways you may use pseudo-elements:

[https://css-tricks.com/7-practical-uses-for-the-before-and-after-pseudo-elements-in-css/](https://css-tricks.com/7-practical-uses-for-the-before-and-after-pseudo-elements-in-css/)
