# GsMVVM

**Gradle**
**Step 1.** Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```css
        dependencyResolutionManagement {
                repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
                repositories {
                    mavenCentral()
                    maven { url 'https://jitpack.io' }
                }
            }
```
**Step 2.** Add the dependency
```css
        dependencies {
                    implementation 'com.github.vtabk2:GsMVVM:1.0.5'
            }
```

# Cách dùng

**Khởi tạo activity**

```css
        class MainActivity : BaseMVVMActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
            override fun setupView(savedInstanceState: Bundle?) {
                super.setupView(savedInstanceState)
        
                val tag = TestFragment::class.java.simpleName
                val testFragment = TestFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flTest, testFragment, tag)
                    .addToBackStack(tag)
                    .commit()
        
                bindingView.flTest.setBackgroundColor(Color.RED)
            }
}
```

**Khởi tạo fragment**

```css
        class TestFragment : BaseMVVMFragment<FragmentTestBinding>(FragmentTestBinding::inflate) {

            override fun initViews(savedInstanceState: Bundle?) {
                super.initViews(savedInstanceState)
        
            }
}
```