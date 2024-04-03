import styled from '@emotion/styled'

interface T_Article {
  img: string
}
export const Container = styled.div<T_Article>(
  {
    position: 'relative',
    width: '20rem',
    height: '90%',
    margin: '0 auto',
    borderRadius: '10px',
    zIndex: '-1',
    overflow: 'hidden',
  },
  ({ img }) => ({
    backgroundImage: `url(${img})`,
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    backgroundColor: 'pink',
  }),
)
export const Content = styled.div({
  color: 'white',
  zIndex: '2',
  position: 'absolute',
  bottom: '15px',
  left: '15px',
})
