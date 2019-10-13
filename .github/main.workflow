workflow "Main workflow" {
  on = "push"
  resolves = ["check", "UI Tests"]
}

action "build" {
  uses = "vgaidarji/android-github-actions/build@v1.0.0"
  args = "\"./gradlew assembleDebug -PpreDexEnable=false"
}

action "check" {
  uses = "vgaidarji/android-github-actions/build@v1.0.0"
  needs = ["build"]
  args = "./gradlew testDebug jacocoTestReport checkstyle pmd jdepend lintDebug buildDashboard -PpreDexEnable=false"
}

action "UI Tests" {
  uses = "vgaidarji/android-github-actions/emulator@v1.0.0"
  needs = ["build"]
}
