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

        <b-modal
          v-model="modalShow"
          title="Agregar Película"
          id="modal-prevent-closing"
          ref="modal"
          @show="resetModal"
          @hidden="resetModal"
          @ok="handleOk"
        >
          <form ref="form" @submit.stop.prevent="handleSubmit">
            <b-form-group
              label="Nombre"
              label-for="name-input"
              invalid-feedback="El nombre es requerido"
              :state="nameState"
            >
              <b-form-input
                id="name-input"
                v-model="movie.name"
                :state="nameState"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group
              label="Director"
              label-for="director-input"
              invalid-feedback="El director es requerido"
              :state="directorState"
            >
              <b-form-input
                id="director-input"
                v-model="movie.director"
                :state="directorState"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group
              label="Duración"
              label-for="duration-input"
              invalid-feedback="La duración es requerida"
              :state="durationState"
            >
              <b-form-input
                id="duration-input"
                v-model="movie.duration"
                :state="durationState"
                required
              ></b-form-input>
            </b-form-group>
            <div class="form-group">
              <label for="genre">Género</label>
              <select class="form-control" id="genre" v-model="movie.genre">
                <option disabled value="">Selecciona un género</option>
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
      name: "",
      nameState: null,
      directorState: null,
      durationState: null,
      genreState: null,
      submittedNames: [],
    };
  },
  methods: {
    checkFormValidity() {
      const valid = this.$refs.form.checkValidity();
      this.nameState = valid;
      this.directorState = valid;
      this.durationState = valid;
      this.genreState = valid;
      return valid;
    },
    resetModal() {
      this.movie.name = "";
      this.movie.director = "";
      this.movie.duration = "";
      this.movie.genre = "";
      this.nameState = null;
      this.directorState = null;
      this.durationState = null;
      this.genreState = null;
    },
    handleOk(bvModalEvent) {
      // Prevent modal from closing
      bvModalEvent.preventDefault();
      // Trigger submit handler
      this.handleSubmit();
    },
    handleSubmit() {
      // Exit when the form isn't valid
      if (!this.checkFormValidity()) {
        return;
      }
      // Push the name to submitted names
      this.submittedNames.push(this.movie.name);
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide("modal-prevent-closing");
      });
    },
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