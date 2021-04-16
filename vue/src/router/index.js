// import Vue from 'vue'
// import Router from 'vue-router'
// import Home from '../views/Home.vue'
// import Login from '../views/Login.vue'
// import Logout from '../views/Logout.vue'
// import Register from '../views/Register.vue'
// //import DoctorHome from '../views/DoctorHome.vue'
// import store from '../store/index'
// import PatientView from '../views/PatientView.vue'
// import StyledHome from '../views/StyledHome.vue'

// Vue.use(Router)

// /**
//  * The Vue Router is used to "direct" the browser to render a specific view component
//  * inside of App.vue depending on the URL.
//  *
//  * It also is used to detect whether or not a route requires the user to have first authenticated.
//  * If the user has not yet authenticated (and needs to) they are redirected to /login
//  * If they have (or don't need to) they're allowed to go about their way.
//  */

// const router = new Router({
//   mode: 'history',
//   base: process.env.BASE_URL,
//   routes: [
//     {
//       path: "/",
//       name: "style",
//       component: StyledHome,
//       meta: {
//         requiresAuth: false, 
//       }
//     },
//     {
//       path: '/home',
//       name: 'home1',
//       component: Home,
//       meta: {
//         requiresAuth: true
//       }
//     },
//     // {
//     //   path: '/doctor-home',
//     //   name: 'doctor-home',
//     //   component: DoctorHome,
//     //   meta: {
//     //     requiresAuth: true
//     //   }
//     // },
//     {
//       path: '/patient',
//       name: 'patient',
//       component: PatientView,
//       meta: {
//         requiresAuth: true,
//         requiresPatient: true
    
//       }
//     },
//     {
//       path: "/login",
//       name: "login",
//       component: Login,
//       meta: {
//         requiresAuth: false, 
//       }
//     },
//     {
//       path: "/logout",
//       name: "logout",
//       component: Logout,
//       meta: {
//         requiresAuth: false
//       }
//     },
//     {
//       path: "/register",
//       name: "register",
//       component: Register,
//       meta: {
//         requiresAuth: false
//       }
//     }
//   ]
// })

// router.beforeEach((to, from, next) => {
//   // Determine if the route requires Authentication
//   // const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

//   const requiresPatient = to.matched.some(x => x.meta.requiresPatient);

//   const requiresDoctor = to.matched.some(x => x.meta.requiresDoctor);

//   const requiresAdmin = to.matched.some(x => x.meta.requiresAdmin);


//   // If it does and they are not logged in, send the user to "/login"
//   if (requiresPatient && store.state.user.authorities[0].name != "ROLE_USER") {
//     next("/login");
//   }else if (requiresDoctor && store.state.user.authorities[0].name != "ROLE_DOCTOR") {
//     next("/login");
//   } else if (requiresAdmin && store.state.user.authorities[0].name != "ROLE_ADMIN") {
//     next("/login"); 
//   } 
//   // else if (requiresAuth && store.state.token === '') {
//   //   next("/");
//   // } 
//   else {
//     // Else let them go to their next destination
//     next();
//   }

  
// });

// export default router;


























import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
//import DoctorHome from '../views/DoctorHome.vue'
import store from '../store/index'
import PatientView from '../views/PatientView.vue'
import StyledHome from '../views/StyledHome.vue'
Vue.use(Router)
/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    // {
    //   path: "/style",
    //   name: "styled-home",
    //   component: StyledHome,
    //   meta: {
    //     requiresAuth: false, 
    //   }
    // },
    {
      path: '/home',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/',
      name: 'styled-home',
      component: StyledHome,
      meta: {
        requiresAuth: true
      }
    },
    // {
    //   path: '/doctor-home',
    //   name: 'doctor-home',
    //   component: DoctorHome,
    //   meta: {
    //     requiresAuth: true
    //   }
    // },
    {
      path: '/patient',
      name: 'patient',
      component: PatientView,
      meta: {
        requiresAuth: true,
        requiresPatient: true
    
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false, 
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    }
  ]
})
router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  // const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  const requiresPatient = to.matched.some(x => x.meta.requiresPatient);
  const requiresDoctor = to.matched.some(x => x.meta.requiresDoctor);
  const requiresAdmin = to.matched.some(x => x.meta.requiresAdmin);
  // If it does and they are not logged in, send the user to "/login"
  if (requiresPatient && store.state.user.authorities[0].name != "ROLE_USER") {
    next("/login");
  }else if (requiresDoctor && store.state.user.authorities[0].name != "ROLE_DOCTOR") {
    next("/login");
  } else if (requiresAdmin && store.state.user.authorities[0].name != "ROLE_ADMIN") {
    next("/login"); 
  } 
  // else if (requiresAuth && store.state.token === '') {
  //   next("/");
  // } 
  else {
    // Else let them go to their next destination
    next();
  }
  
});
export default router;