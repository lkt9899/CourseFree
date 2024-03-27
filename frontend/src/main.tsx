import { ChakraProvider } from '@chakra-ui/react'
import { Global } from '@emotion/react'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { ReactQueryDevtools } from '@tanstack/react-query-devtools'
import ReactDOM from 'react-dom/client'
import { RecoilRoot } from 'recoil'

import App from './App.tsx'
import './index.css'

import globalStyles from '@style/globalStyles.ts'

const queryClient = new QueryClient()

ReactDOM.createRoot(document.getElementById('root')!).render(
  // <React.StrictMode>

  <RecoilRoot>
    <ChakraProvider>
      <QueryClientProvider client={queryClient}>
        <Global styles={globalStyles} />
        <App />
        <ReactQueryDevtools />
      </QueryClientProvider>
    </ChakraProvider>
  </RecoilRoot>,

  // </React.StrictMode>,
)
