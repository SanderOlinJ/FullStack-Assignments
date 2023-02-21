<template>
  <nav>
    <router-link to="/">Calculator</router-link>
    <router-link to="/contact">Contact</router-link>
  </nav>
  <router-view />
</template>


<style>
body{
  background: linear-gradient(109.6deg, rgb(78, 62, 255) 11.2%, rgb(164, 69, 255) 91.1%);
  align-items: center;
  justify-content: center;
  display: grid;
}

html{
  justify-content: center;
  align-items: center;
  height: 100vh;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  justify-content: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  font-size: 30px;
  color: black;
  margin: 10px;
  text-decoration: none;
}

nav a.router-link-exact-active {
  color: white;
  text-decoration: underline;
}

</style>