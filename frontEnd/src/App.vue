<template>
  <div id="app">
    <TheHeader />
    <the-content>
      <router-view/>
    </the-content>
  </div>
</template>
<script>



export default {
  name: "App",

}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100vh;

  background: linear-gradient(90deg, #e4c9ac 0%, rgba(255, 255, 255, 0) 100%);

}
</style>
