import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],

    // Make the build location be ../src/main/resources/static
    build: {
        outDir: '../src/main/resources/static'
    }
})
