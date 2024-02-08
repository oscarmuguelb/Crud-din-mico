<template>
  <div class="container">
    <div class="loading-overlay is-active" v-if="isLoading">
      <b-spinner class="spiner"></b-spinner>
    </div>
    <h1>Películas</h1>
    <b-button @click="modalShow = !modalShow" variant="primary">Agregar</b-button>
    <div class="card-container">
      <b-card v-for="movie in items" :key="movie.nombre" class="movie-card" bg-variant="dark" text-variant="white">
        <b-card-title>{{ movie.nombre }}</b-card-title>
        <b-card-text>{{ movie.director }}</b-card-text>
        <b-card-text>{{ movie.duracion }}</b-card-text>
        <b-card-text>{{ movie.genero.nombre }}</b-card-text>
        <div class="optionsCont">
          <b-button class="optionsCards" variant="warning" @click="handleShowEdit(movie)">
            <b-icon icon="pencil-fill"></b-icon>
          </b-button>
          <b-button class="optionsCards" variant="danger" @click="handleDelete(movie.id_pelicula)">
            <b-icon icon="trash-fill"></b-icon>
          </b-button>
        </div>
      </b-card>
    </div>
    <div class="modal-container">
      <div class="modal-content">
        <b-modal v-model="modalShow" title="Datos de la pelicula" id="modal-prevent-closing" ref="modal" @show="resetModal" @hidden="resetModal" @ok="handleOk">
          <form ref="form" @submit.stop.prevent="handleSubmit">
            <b-form-group label="Nombre" label-for="nombre-input" invalid-feedback="El nombre es requerido" :state="nombreState">
              <b-form-input id="nombre-input" v-model="movie.nombre" :state="nombreState" required></b-form-input>
            </b-form-group>
            <b-form-group label="Director" label-for="director-input" invalid-feedback="El director es requerido" :state="directorState">
              <b-form-input id="director-input" v-model="movie.director" :state="directorState" required></b-form-input>
            </b-form-group>
            <b-form-group label="Duración" label-for="duracion-input" invalid-feedback="La duración es requerida" :state="duracionState">
              <b-form-input id="duracion-input" v-model="movie.duracion" :state="duracionState" required  type="number" min="0"></b-form-input>
            </b-form-group>
            <div class="form-group">
              <label for="genero">Género</label>
              <select class="form-control" id="genero" v-model="movie.genero" required :state="generoState">
                <option disabled value="">Selecciona un género</option>
                <option v-for="genero in generos" :key="genero.id_genero" :value="genero">
                  {{ genero.nombre }}
                </option>
              </select>
              <div v-if="!generoState" class="invalid-feedback">El género es requerido</div>
            </div>
          </form>
        </b-modal>
      </div>
    </div>
  </div>
</template>

<script>
import { obtenerPeliculas, savePelicula, updatePelicula, deletePelicula } from '../services/PeliculasService'

import { obtenerGeneros } from '../services/GeneroService'
export default {
  data() {
    return {
      isLoading: false,
      modalShow: false,
      movie: {},
      items: [],
      generos: [],
      duracionState: null,
      nombreState: null,
      directorState: null,
      generoState: null,
    }
  },
  methods: {
    checkFormValidity() {
      const valid = this.$refs.form.checkValidity()
      this.nombreState = valid
      this.directorState = valid
      this.duracionState = valid
      this.generoState = valid
      return valid
    },
    resetModal() {
      if (!this.movie.id_pelicula) {
        this.movie.nombre = ''
        this.movie.director = ''
        this.movie.duracion = ''
        this.movie.genero = ''
      }
      this.nombreState = null
      this.directorState = null
      this.duracionState = null
      this.generoState = null
    },

    handleOk(bvModalEvent) {
      bvModalEvent.preventDefault()
      this.handleSubmit()
    },
    async handleSubmit() {
      try {
        this.$refs.form.classList.add('was-validated')
        if (!this.checkFormValidity()) {
          return
        }
        this.isLoading = true
        if (this.movie.id_pelicula) {
          await updatePelicula(this.movie)
          this.movie = {}
        } else {
          await savePelicula(this.movie)
        }

        this.getData()
        this.$nextTick(() => {
          this.$bvModal.hide('modal-prevent-closing')
        })
        this.isLoading = false
        this.modalShow = false
        alert('Película guardada correctamente')
      } catch (error) {
        console.error(error)
        this.isLoading = false
        this.$nextTick(() => {
          this.$bvModal.hide('modal-prevent-closing')
        })
        this.isLoading = false
        this.modalShow = false
        alert('Ocurrió un error al guardar la película')
      }
    },
    async handleDelete(id) {
      try {
        this.isLoading = true
        await deletePelicula(id)
        this.getData()
        this.isLoading = false
        alert('Película eliminada correctamente')
      } catch (error) {
        console.error(error)
        this.isLoading = false
        alert('Ocurrió un error al eliminar la película')
      }
    },

    handleShowEdit(movie) {
      console.log(movie)
      this.movie = { ...movie }
      this.modalShow = true
    },

    async getData() {
      try {
        this.isLoading = true
        this.items = await obtenerPeliculas()
        this.generos = await obtenerGeneros()
        this.isLoading = false
      } catch (error) {
        console.error(error)
        this.isLoading = false
        alert('Ocurrió un error al obtener las películas chance no hay conexión a internet')
      }
    },
  },
  async mounted() {
    await this.getData()
  },
}
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
.loading-overlay {
  display: none;
  background: rgba(255, 255, 255, 0.7);
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  top: 0;
  z-index: 9998;
  align-items: center;
  justify-content: center;
}

.loading-overlay.is-active {
  display: flex;
}

.code {
  font-family: monospace;
  /*   font-size: .9em; */
  color: #dd4a68;
  background-color: rgb(238, 238, 238);
  padding: 0 3px;
}

.spiner {
  color: #dd4a68;
}
</style>
