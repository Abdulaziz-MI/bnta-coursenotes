# React Router

## Learning Objectives

* Identify what a SPA is, along with the advantages and disadvantages
* Know what React Router does, and be able to install it
* Be able to implement a multi-page website design using Router

## What is routing?

Thus far, our React apps have the same URL no matter where in the app we are; this is because, out of the box, react is designed for **SPA** (single page applications). This means we cannot link directly to specific pages or sections in our applications. The URL never changes, and we cannot link to different parts of the webpage as we would on a traditional website.

In practice, we sometimes want websites that have different paths (urls) for different pages - this would reflect the classic, multi-page structure most users are accustomed to on the web.

This is because on a non-React app, we would normally store different HTML files on the server (or perhaps dynamically generate those files on the server) and requests to different URLS would result in different pages being sent back to the client as responses. But this is not the model for a Single Page App (SPA). If we did something like this, we would be at the mercy of the request-response cycle...which is, after all, one of the reasons why we switched to React. We want to use Javascript in the browser to make the changes we want to see.

React Router can give the the best of both worlds. Page changes are handled by our client-side code (React) and any visible content changes are done without fetching a new HTML file. So, we get the functionality of a multi-page website, with unique URLs for the different sections, but without the time delay of having to constantly send GET requests to the server for various files.

## Start Code

Please pull the start_code provided from your coursenotes repo. Your App.js file should look like this:

```javascript
import "./App.css";

function App() {
  return (
    <div className="App">
      <h1>Hello BNTA World</h1>
    </div>
  );
}

export default App;
```

This is a fairly blank react setup; it provides a clean canvas for us to build upon. Please run `npm i` to install the required dependencies in `node_modules`.

## Installing React Router

React Router doesn't come as standard, so we need to install it explicitally.

There are a couple of different versions being used widely: v5 and v6. We will show you how to implement the latest version (v 6), which is a much improved version under the hood. The good news is that if you ever encounter v5, you will find implementing it much the same. React Router have a page in their documentation that specifically refers to upgrading from v5 to v6 should you ever need it. Your App.js file should like like this:

Ensuring that you are at the root of your project, run:

```sh
# Terminal
npm install react-router-dom@6
```

## Writing our routes

First, we'll need to add an import statement so we have access to the Components we need from react-router-dom, which is the name of the package we just installed into node_modules:

```javascript
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
```

We're importing them in approximately the order in which we will be using them. The first component we'll use is `BrowserRouter`. This functions as the wrapper for all of our wroting. Essentially, you want to wrap your entire application inside of it like so:

```javascript
function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <h1>Hello BNTA World</h1>
      </div>
    </BrowserRouter>
  );
}
```

Lovely! Now we can start adding our routes, along with hyperlinks to navigate between them, using the other componenets we imported earlier - `Routes`, `Route` and `Link`.

## Routes and Route

Under the h1 tag, please add the following element:

```javascript
<Routes></Routes>
```

Inside of this routes wrapper, we can add a Route for each path.

```javascript
<Routes>
  <Route path="/" />
  <Route path="/about" />
  <Route path="/services" />
  <Route path="/contact" />
</Routes>
```

As you can see, we use the path attribute to set the endpoint for a particular route. These routes will not actually work yet however, since we haven't told them what component to load when clicked. We do that with the element attribute. Let's add an element attribute to each Route now:

```javascript
   <Routes>
        <Route path='/' element={} />
        <Route path='/about' element={} />
        <Route path='/services' element={} />
        <Route path='/contact' element={} />
    </Routes>

```

You will likely have noticed that, for each element attribute, we have assigned an empty target of `{}` - this is because we haven't actually created the components yet. Once we do, we'll insert them in the same way as we would in standard JSX.

## Endpoint Components

Since we have a home, about, services and contact page, we will create `Home`, `About`, `Services` and `Contact` components to represent these. In the src directory, please go ahead and create a Home.js file, and add the following code:

```javascript
function Home() {
    return <><h2>Home Page</h2></>
    <p> This is the amazing homepage of the Hello BNTA app!</p>
}

export default Home;
```

This just defines a component called `Home`, which returns a h2 tag which says 'Home Page', and a simple paragraph element. Theoretically, it could contain anything you desire, just like any other react component. The last line in the file exports the the component we just created so we can import it elsewhere. Back in App.js, please add the following `import` statement:

```javascript
import Home from "./Home";
```

Once you've done that, you can add an instance of the `Home` component to the home route we created earlier, in between the `{}` in the `element` attribute:

```javascript
<Route path="/" element={<Home />} />
```

## Create Additional Components

We still need to create components for `About`, `Services` and `Contact`. Please do so now following the same steps as you did above for `Home`. Once you've imported them into App.js, and inserted them into the `element` attribute for the relevant `Route`, you should have something that looks like this:

```javascript
<BrowserRouter>
  <div className="App">
    <h1>Hello BNTA World</h1>
    {/* Switch is replaced by Routes in v6 */}
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/about" element={<About />} />
      <Route path="/services" element={<Services />} />
      <Route path="/contact" element={<Contact />} />
    </Routes>
  </div>
</BrowserRouter>
```

## Links

This all looks great, but if you were to test it, you would run into a fatal flaw - as yet, there is no mechanism for actually navigating between these routes. For that, we need to use the Link component. To do so, please create an unordered list with 4 `li` elements. We will then add a `Link` element to each `li`, with a `to` attribute which defines the _endpoint_ we are targeting. It should look something like this:

```javascript
<ul>
  <li>
    <Link to="/">Home</Link>
  </li>
  <li>
    <Link to="/about">About </Link>
  </li>
  <li>
    <Link to="/services">Services</Link>
  </li>
  <li>
    <Link to="/contact">Contact</Link>
  </li>
</ul>
```

As you can see, the key to working Links is to assign the appropriate endpoint via the to attribute. This should match up with the respective path attribute of the Route your targeting - for example, take a look at the Routes section again, for example:

```javascript
<Route path="/about" element={<About />} />
```

Which of the Link elements we added earlier do you think will lead us to the About route? Yes, the one with the _matching_ `to` value:

```javascript
<li>
  <Link to="/about">About </Link>
</li>
```

## Finish Code

If you followed the lesson correctly, you should now have an App.js file that looks like this:

```javascript
import "./App.css";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import Home from "./Home";
import About from "./About";
import Services from "./Services";
import Contact from "./Contact";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <h1>Hello BNTA World</h1>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/about">About </Link>
          </li>
          <li>
            <Link to="/services">Services</Link>
          </li>
          <li>
            <Link to="/contact">Contact</Link>
          </li>
        </ul>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/services" element={<Services />} />
          <Route path="/contact" element={<Contact />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
```
