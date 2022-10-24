<template>
  <div>hello {{value}}
    <input type="checkbox" v-model="value" value="phong1" />
    <input type="checkbox" v-model="value" value="phong2" />
    <input type="checkbox" v-model="value" value="phong3" />
  </div>
</template>

<script>
export default {
  data() {
    return {
      value: [],
    };
  },
};
</script>

<style>
</style>>