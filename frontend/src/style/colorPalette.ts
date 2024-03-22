import { css } from '@emotion/react'

export const colorPalette = css`
  :root {
    --red: #f44336;
    --blue: #2196f3;
    --green: #4caf50;
    --white: #fff;
    --black: #212121;
    --grey: #f0efef;
    --yellow: #fee500;
    --gray: #888888;
    --pink50: #fff5f7;
    --pink100: #fed7e2;
    --pink200: #fbb6ce;
    --pink300: #f687b3;
    --pink400: #ed64a6;
    --pink500: #d53f8c;
    --pink600: #b83280;
    --pink700: #97266d;
    --pink800: #702459;
    --pink900: #521b41;
    --primary: #f687b3;
  }
`

export const colors = {
  red: 'var(--red)',
  blue: 'var(--blue)',
  green: 'var(--green)',
  white: 'var(--white)',
  black: 'var(--black)',
  grey: 'var(--grey)',
  gray: 'var(--gray)',
  yellow: 'var(--yellow)',
  pink50: 'var(--pink50)',
  pink100: 'var(--pink100)',
  pink200: 'var(--pink200)',
  pink300: 'var(--pink300)',
  pink400: 'var(--pink400)',
  pink500: 'var(--pink500)',
  pink600: 'var(--pink600)',
  pink700: 'var(--pink700)',
  pink800: 'var(--pink800)',
  pink900: 'var(--pink900)',
  primary: 'var(--primary)',
}

export type Colors = keyof typeof colors
