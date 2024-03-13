import { Outlet } from 'react-router-dom'

import Footer from '@component/layout/Footer'
import Section from '@component/layout/Section'
import HomeNavLink from '@component/pages/HomePage/HomeNavLink'

function HomePage() {
  return (
    <>
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
