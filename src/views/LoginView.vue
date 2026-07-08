<template>
  <div class="login-container">
    <h2>Login</h2>
    <form novalidate @submit.prevent="handleLogin">
      <div class="form-group">
        <label for="username">Username</label>
        <input
          id="username"
          v-model="username"
          type="text"
          placeholder="Enter your username"
          :class="{ invalid: fieldError('username') }"
          @input="clearFieldError('username')"
        />
        <p v-if="fieldError('username')" class="field-error">{{ fieldError('username') }}</p>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input
          id="password"
          v-model="password"
          type="password"
          placeholder="Enter your password"
          :class="{ invalid: fieldError('password') }"
          @input="clearFieldError('password')"
        />
        <p v-if="fieldError('password')" class="field-error">{{ fieldError('password') }}</p>
      </div>
      <div v-if="authStore.error" class="error-message">
        {{ authStore.error }}
      </div>
      <button type="submit" :disabled="authStore.isLoading">
        {{ authStore.isLoading ? 'Logging in...' : 'Login' }}
      </button>
    </form>
    <p>
      Don't have an account? <router-link to="/register">Register</router-link>
    </p>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const router = useRouter()
const authStore = useAuthStore()
const username = ref('')
const password = ref('')
const localErrors = ref<Record<string, string>>({})

function fieldError(field: string) {
  return localErrors.value[field] || authStore.fieldErrors[field] || ''
}

function clearFieldError(field: string) {
  delete localErrors.value[field]
  delete authStore.fieldErrors[field]
  authStore.error = null
}

function validateForm() {
  localErrors.value = {}

  if (!username.value.trim()) {
    localErrors.value.username = 'Username is required'
  }

  if (!password.value) {
    localErrors.value.password = 'Password is required'
  }

  return Object.keys(localErrors.value).length === 0
}

async function handleLogin() {
  authStore.clearErrors()

  if (!validateForm()) {
    return
  }

  const success = await authStore.login(username.value, password.value)
  if (success) {
    router.push('/dashboard')
  }
}
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
}
.form-group {
  margin-bottom: 15px;
}
.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}
.form-group input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}
.form-group input.invalid {
  border-color: #dc3545;
}
.field-error {
  margin-top: 5px;
  color: #dc3545;
  font-size: 0.85rem;
}
.error-message {
  color: red;
  margin: 10px 0;
  padding: 10px;
  background: #fee;
  border-radius: 4px;
}
button {
  width: 100%;
  padding: 10px;
  background: #42b883;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}
</style>
