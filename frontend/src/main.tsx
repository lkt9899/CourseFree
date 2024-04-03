import { ChakraProvider } from '@chakra-ui/react'
import { queryClient } from '@data/queryClient.ts'
import { Global } from '@emotion/react'
import { QueryClientProvider } from '@tanstack/react-query'
import { ReactQueryDevtools } from '@tanstack/react-query-devtools'
import ReactDOM from 'react-dom/client'
import { RecoilRoot } from 'recoil'

import App from './App.tsx'
import './index.css'

import chakraTheme from '@style/chakraTheme.ts'
import globalStyles from '@style/globalStyles.ts'

ReactDOM.createRoot(document.getElementById('root')!).render(
  // <React.StrictMode>

  <RecoilRoot>
    <ChakraProvider theme={chakraTheme}>
      <QueryClientProvider client={queryClient}>
        <Global styles={globalStyles} />
        <App />
        <ReactQueryDevtools />
      </QueryClientProvider>
    </ChakraProvider>
  </RecoilRoot>,

  // </React.StrictMode>,
)
