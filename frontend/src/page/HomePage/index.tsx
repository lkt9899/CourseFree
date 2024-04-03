import { Outlet } from 'react-router-dom'

import Footer from '@component/layout/Footer'
import Section from '@component/layout/Section'
import HomeNavLink from '@component/pages/HomePage/HomeNavLink'

function HomePage() {
  return (
    <>
      {/* <Lottie
        animationData={Flower}
        style={{
          position: 'absolute',
          top: '0',
          width: '100%',
          height: '200px',
        }}
      ></Lottie> */}
      <Section>
        {/* <Spacing size="0.5rem" /> */}
        <HomeNavLink />
        {/* <Spacing size="1rem" /> */}
        <Outlet></Outlet>
      </Section>
      <Footer />
    </>
  )
}

export default HomePage
