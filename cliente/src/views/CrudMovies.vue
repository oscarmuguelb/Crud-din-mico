<template>
  <div class="container">
    <div class="card-container">
      <b-card
        v-for="movie in items"
        :key="movie.name"
        class="movie-card"
        bg-variant="dark"
        text-variant="white"
      >
        <b-card-title>{{ movie.name }}</b-card-title>
        <b-card-text>{{ movie.director }}</b-card-text>
        <b-card-text>{{ movie.duration }}</b-card-text>
        <b-card-text>{{ movie.genre }}</b-card-text>
        <div class="optionsCont">
          <b-button class="optionsCards" variant="warning">
            <b-icon icon="pencil-fill"></b-icon>
          </b-button>
          <b-button class="optionsCards" variant="danger">
            <b-icon icon="trash-fill"></b-icon>
          </b-button>
        </div>
      </b-card>
    </div>
    <div class="modal-container">
      <div class="modal-content">
        <b-button @click="modalShow = !modalShow">Agregar</b-button>

        <b-modal v-model="modalShow" title="Título del Modal">
          <form>
            <div class="form-group">
              <label for="name">Nombre</label>
              <input
                type="text"
                class="form-control"
                id="name"
                v-model="movie.name"
              />
            </div>
            <div class="form-group">
              <label for="director">Director</label>
              <input
                type="text"
                class="form-control"
                id="director"
                v-model="movie.director"
              />
            </div>
            <div class="form-group">
              <label for="duration">Duración</label>
              <input
                type="text"
                class="form-control"
                id="duration"
                v-model="movie.duration"
              />
            </div>
            <div class="form-group">
              <label for="genre">Género</label>
              <select class="form-control" id="genre" v-model="movie.genre">
                <option
                  v-for="genre in genres"
                  :key="genre.id"
                  :value="genre.id"
                >
                  {{ genre.name }}
                </option>
              </select>
            </div>
          </form>
        </b-modal>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      modalShow: false,
      movie: {
        name: "",
        director: "",
        duration: "",
        genre: "",
      },
      items: [
        {
          name: "Película 1",
          director: "Director 1",
          duration: "120 min",
          genre: "Acción",
        },
        {
          name: "Película 2",
          director: "Director 2",
          duration: "90 min",
          genre: "Comedia",
        },
        {
          name: "Película 3",
          director: "Director 3",
          duration: "105 min",
          genre: "Drama",
        },
        {
          name: "Película 4",
          director: "Director 4",
          duration: "95 min",
          genre: "Thriller",
        },
        {
          name: "Película 5",
          director: "Director 4",
          duration: "95 min",
          genre: "Thriller",
        },
        {
          name: "Película 5",
          director: "Director 4",
          duration: "95 min",
          genre: "Thriller",
        },
        {
          name: "Película 5",
          director: "Director 4",
          duration: "95 min",
          genre: "Thriller",
        },
        {
          name: "Película 5",
          director: "Director 4",
          duration: "95 min",
          genre: "Thriller",
        },
      ],
      genres: [
        { id: 1, name: "Acción" },
        { id: 2, name: "Comedia" },
        { id: 3, name: "Drama" },
        { id: 4, name: "Thriller" },
      ],
    };
  },
};
</script>

<style scoped>
.container {
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: antiquewhite;
}
.optionsCont {
  flex-direction: row;
  display: flex;
  justify-content: space-around;
  align-items: center;
  margin: 10px;
  
}
.card-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}

.movie-card {
  margin: 10px;
  width: 200px;
}
.optionsCards {

  margin: 10px;
}
.modal-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>