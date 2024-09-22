package pe.edu.upeu.asistenciaupeujcn

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujcn.modelo.Estudiante
import pe.edu.upeu.asistenciaupeujcn.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujcn.ui.navigation.NavigationHost
import pe.edu.upeu.asistenciaupeujcn.ui.presentation.components.AppDrawer
import pe.edu.upeu.asistenciaupeujcn.ui.presentation.components.CustomTopAppBar
import pe.edu.upeu.asistenciaupeujcn.ui.presentation.components.Dialog
import pe.edu.upeu.asistenciaupeujcn.ui.presentation.components.FabItem
import pe.edu.upeu.asistenciaupeujcn.ui.presentation.screens.login.EstudianteViewModel
import pe.edu.upeu.asistenciaupeujcn.ui.theme.*
import pe.edu.upeu.asistenciaupeujcn.utils.TokenUtils
import pe.edu.upeu.asistenciaupeujcn.utils.isNight

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val themeType = remember { mutableStateOf(ThemeType.RED) }
            val darkThemex = isNight()
            val darkTheme = remember { mutableStateOf(darkThemex) }
            val colorScheme = when (themeType.value) {
                ThemeType.PURPLE -> {
                    if (darkTheme.value) DarkPurpleColors else LightPurpleColors
                }
                ThemeType.RED -> {
                    if (darkTheme.value) DarkRedColors else LightRedColors
                }
                ThemeType.GREEN -> {
                    if (darkTheme.value) DarkGreenColors else LightGreenColors
                }
                else -> {
                    LightRedColors
                }
            }
            TokenUtils.CONTEXTO_APPX = this@MainActivity

            // Aplicar el esquema de colores
            AsistenciaUPeUJCNTheme(colorScheme = colorScheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    MainScreen(
                        navController = navController,
                        darkMode = darkTheme,
                        themeType = themeType,
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    navController: NavHostController,
    darkMode: MutableState<Boolean>,
    themeType: MutableState<ThemeType>,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDialog = remember { mutableStateOf(false) }
    val navigationItems = listOf(
        Destinations.Pantalla1,
        Destinations.Pantalla2,
        Destinations.Pantalla3,
        Destinations.Pantalla4,
        Destinations.Pantalla5,
        Destinations.EstudianteScreen,
    )
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: Destinations.Pantalla1.route
    val list = currentRoute.split("/", "?")

    ModalNavigationDrawer(
        drawerContent = {
            AppDrawer(
                route = list[0], scope = scope,
                scaffoldState = drawerState,
                navController = navController, items = navigationItems
            )
        },
        drawerState = drawerState
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        val context = LocalContext.current
        val fabItems = listOf(
            FabItem(
                Icons.Filled.ShoppingCart,
                "Shopping Cart"
            ) {
                val toast = Toast.makeText(context, "Hola Mundo", Toast.LENGTH_LONG)
                toast.view!!.background.setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN)
                toast.show()
            },
            FabItem(
                Icons.Filled.Favorite,
                "Favorite"
            ) { /*TODO*/ }
        )

        Scaffold(
            topBar = {
                CustomTopAppBar(
                    list[0],
                    darkMode = darkMode,
                    themeType = themeType,
                    navController = navController,
                    scope = scope,
                    scaffoldState = drawerState,
                    openDialog = { openDialog.value = true }
                )
            },

        ) {
            NavigationHost(navController, darkMode, modif = it)
        }
    }
    Dialog(showDialog = openDialog.value, dismissDialog = {
        openDialog.value = false
    })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AsistenciaUPeUJCNTheme(colorScheme = LightPurpleColors) {
        Text("Android")
    }
}

