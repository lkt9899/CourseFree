import { css } from '@emotion/react'

export const addStyle = css`
  :root {
    /* overflow-y: scroll; */
    /* overscroll-behavior-y: contain; */
    input:focus {
      outline: none;
      border-bottom: 1px solid hotpink;
    }
    progress {
      -webkit-appearance: none;
    }

    progress::-webkit-progress-bar {
      background-color: var(--grey);
    }

    progress::-webkit-progress-value {
      background-color: var(--primary);
    }
  }
  .swiper-slide {
    opacity: 1 !important;
    transition: none !important;
  }
`
