
<template>
    <div class="container">
        <b-button @click="modalShow = !modalShow" variant="primary">Agregar</b-button>
        <div class="card-container">
            <b-card v-for="movie in items" :key="movie.name" class="movie-card" bg-variant="dark" text-variant="white">
                <b-card-title>{{ movie.name }}</b-card-title>
                <b-card-text>{{ movie.director }}</b-card-text>
                <b-card-text>{{ movie.duracion }}</b-card-text>
                <b-card-text>{{ movie.genero.nombre }}</b-card-text>
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
                <b-modal v-model="modalShow" title="Agregar Película" id="modal-prevent-closing" ref="modal"
                    @show="resetModal" @hidden="resetModal" @ok="handleOk">
                    <form ref="form" @submit.stop.prevent="handleSubmit">
                        <b-form-group label="Nombre" label-for="name-input" invalid-feedback="El nombre es requerido"
                            :state="nameState">
                            <b-form-input id="name-input" v-model="movie.name" :state="nameState" required></b-form-input>
                        </b-form-group>
                        <b-form-group label="Director" label-for="director-input"
                            invalid-feedback="El director es requerido" :state="directorState">
                            <b-form-input id="director-input" v-model="movie.director" :state="directorState"
                                required></b-form-input>
                        </b-form-group>
                        <b-form-group label="Duración" label-for="duracion-input" type="number" min="0"
                            invalid-feedback="La duración es requerida" :state="duracionState">
                            <b-form-input id="duracion-input" v-model="movie.duracion" :state="duracionState"
                                required></b-form-input>
                        </b-form-group>
                        <div class="form-group">
                            <label for="genero">Género</label>
                            <select class="form-control" id="genero" v-model="movie.genero">
                                <option disabled value="">Selecciona un género</option>
                                <option v-for="genero in generos" :key="genero.id_genero" :value="genero">
                                    {{ genero.nombre }}
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
import { obtenerPeliculas } from "../services/PeliculasService";

import { obtenerGeneros } from "../services/GeneroService";
export default {
    data() {
        return {
            modalShow: false,
            movie: {
                name: "",
                director: "",
                duracion: "",
                genero: "",
            },
            items: [],
            generos: [],
        };
    },
    methods: {
        checkFormValidity() {
            const valid = this.$refs.form.checkValidity();
            this.nameState = valid;
            this.directorState = valid;
            this.duracionState = valid;
            this.generoState = valid;
            return valid;
        },
        resetModal() {
            this.movie.name = "";
            this.movie.director = "";
            this.movie.duracion = "";
            this.movie.genero = "";
            this.nameState = null;
            this.directorState = null;
            this.duracionState = null;
            this.generoState = null;
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
    async mounted() {
        this.items = await obtenerPeliculas();
        this.generos = await obtenerGeneros();console.log(this.generos);    },
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