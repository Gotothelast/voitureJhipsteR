<template>
  <div>
    <h2 id="page-heading" data-cy="MechanicHeading">
      <span v-text="t$('voitureJhipsteRApp.mechanic.home.title')" id="mechanic-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('voitureJhipsteRApp.mechanic.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'MechanicCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-mechanic"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('voitureJhipsteRApp.mechanic.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && mechanics && mechanics.length === 0">
      <span v-text="t$('voitureJhipsteRApp.mechanic.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="mechanics && mechanics.length > 0">
      <table class="table table-striped" aria-describedby="mechanics">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('voitureJhipsteRApp.mechanic.motor')"></span></th>
            <th scope="row"><span v-text="t$('voitureJhipsteRApp.mechanic.power')"></span></th>
            <th scope="row"><span v-text="t$('voitureJhipsteRApp.mechanic.km')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="mechanic in mechanics" :key="mechanic.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'MechanicView', params: { mechanicId: mechanic.id } }">{{ mechanic.id }}</router-link>
            </td>
            <td>{{ mechanic.motor }}</td>
            <td>{{ mechanic.power }}</td>
            <td>{{ mechanic.km }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'MechanicView', params: { mechanicId: mechanic.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'MechanicEdit', params: { mechanicId: mechanic.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(mechanic)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span
          id="voitureJhipsteRApp.mechanic.delete.question"
          data-cy="mechanicDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-mechanic-heading" v-text="t$('voitureJhipsteRApp.mechanic.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-mechanic"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeMechanic()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./mechanic.component.ts"></script>
