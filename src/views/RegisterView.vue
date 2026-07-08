<template>
  <div class="register-container">
    <h2>Create Account</h2>
    <form novalidate @submit.prevent="handleRegister">
      <div class="form-group">
        <label for="username">Username</label>
        <input
          id="username"
          v-model="username"
          type="text"
          placeholder="Choose a username"
          :class="{ invalid: fieldError('username') }"
          @input="clearFieldError('username')"
        />
        <p v-if="fieldError('username')" class="field-error">{{ fieldError('username') }}</p>
      </div>
      <div class="form-group">
        <label for="fullName">Full Name</label>
        <input
          id="fullName"
          v-model="fullName"
          type="text"
          placeholder="Enter your full name"
          :class="{ invalid: fieldError('fullName') }"
          @input="clearFieldError('fullName')"
        />
        <p v-if="fieldError('fullName')" class="field-error">{{ fieldError('fullName') }}</p>
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input
          id="email"
          v-model="email"
          type="email"
          placeholder="Enter your email"
          :class="{ invalid: fieldError('email') }"
          @input="clearFieldError('email')"
        />
        <p v-if="fieldError('email')" class="field-error">{{ fieldError('email') }}</p>
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
      <div class="form-group">
        <label for="confirmPassword">Confirm Password</label>
        <input
          id="confirmPassword"
          v-model="confirmPassword"
          type="password"
          placeholder="Confirm your password"
          :class="{ invalid: fieldError('confirmPassword') }"
          @input="clearFieldError('confirmPassword')"
        />
        <p v-if="fieldError('confirmPassword')" class="field-error">{{ fieldError('confirmPassword') }}</p>
      </div>
      <div v-if="authStore.error" class="error-message">
        {{ authStore.error }}
      </div>
      <button type="submit" :disabled="authStore.isLoading">
        {{ authStore.isLoading ? 'Creating account...' : 'Register' }}
      </button>
    </form>
    <p>
      Already have an account? <router-link to="/login">Login</router-link>
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
const fullName = ref('')
const email = ref('')
const password = ref('')
const confirmPassword = ref('')
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

  if (!fullName.value.trim()) {
    localErrors.value.fullName = 'Full name is required'
  }

  if (!email.value.trim()) {
    localErrors.value.email = 'Email is required'
  } else if (!/^\S+@\S+\.\S+$/.test(email.value)) {
    localErrors.value.email = 'Enter a valid email address'
  }

  if (!password.value) {
    localErrors.value.password = 'Password is required'
  } else if (password.value.length < 6) {
    localErrors.value.password = 'Password must be at least 6 characters'
  }

  if (!confirmPassword.value) {
    localErrors.value.confirmPassword = 'Please confirm your password'
  } else if (password.value !== confirmPassword.value) {
    localErrors.value.confirmPassword = 'Passwords do not match'
  }

  return Object.keys(localErrors.value).length === 0
}

async function handleRegister() {
  authStore.clearErrors()

  if (!validateForm()) {
    return
  }

  const success = await authStore.register(
    username.value,
    password.value,
    email.value,
    fullName.value
  )
  
  if (success) {
    router.push('/dashboard')
  }
}
</script>

<style scoped>
.register-container {
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
