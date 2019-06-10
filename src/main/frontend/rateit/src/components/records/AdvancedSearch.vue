<template>
  <div class="search">
    <div class="col-sm-12 col-md-7">
      <div class="panel panel-default">
        <h1 style="text-align: center;">Advanced search</h1>
        <div id="searchBar">
          <div class="searchBar-form">
            <form @submit.prevent="onSubmit">
              <div class="input">
                <label for="yearFrom">From year</label>
                <input
                  type="number"
                  id="yearFrom"
                  v-model="yearFrom">
              </div>
              <div class="input">
                <label for="yearTo">To year</label>
                <input
                  type="number"
                  id="yearTo"
                  v-model="yearTo">
              </div>
              <div class="submit">
                <button type="submit">Submit</button>
              </div>
            </form>
            <p>{{yearFrom}} - {{yearTo}}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        yearFrom: null,
        yearTo: null,
      }
    },
    methods: {
      onSubmit(){
        const search = {
          yearFrom: this.yearFrom,
          yearTo: this.yearTo
        };
        this.$emit('advancedSearch', search)
      }
    }
  }
</script>


<style scoped>
  .searchBar-form {
    width: 400px;
    margin: 30px auto;
    border: 1px solid #eee;
    padding: 20px;
    box-shadow: 0 2px 3px #ccc;
  }

  .input {
    margin: 10px auto;
  }


  .input label {
    display: block;
    color: #4e4e4e;
    margin-bottom: 6px;
  }

  .input input {
    font: inherit;
    width: 100%;
    padding: 6px 12px;
    box-sizing: border-box;
    border: 1px solid #ccc;
  }

  .input input:focus {
    outline: none;
    border: 1px solid #521751;
    background-color: #eee;
  }

  .submit button {
    border: 1px solid #521751;
    color: #521751;
    padding: 10px 20px;
    font: inherit;
    cursor: pointer;
  }

  .submit button:hover,
  .submit button:active {
    background-color: #521751;
    color: white;
  }

  .submit button[disabled],
  .submit button[disabled]:hover,
  .submit button[disabled]:active {
    border: 1px solid #ccc;
    background-color: transparent;
    color: #ccc;
    cursor: not-allowed;
  }
</style>
