; --- Pharmacie App Installer ---

#define MyAppName "Pharmacie App"
#define MyAppVersion "1.0"
#define MyAppPublisher "FST Marrakech"
#define MyAppURL "https://github.com/malakoub1"
#define MyAppExeName "run.bat"

[Setup]
AppId={{EBD9845B-D5F0-4303-9AE9-C70C714ECED3}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}
DefaultDirName={autopf}\PharmacieApp
DisableProgramGroupPage=yes
OutputBaseFilename=PharmacieSetup
Compression=lzma
SolidCompression=yes
WizardStyle=modern

; Icône de l’installer (chemin)
SetupIconFile=C:\Users\PC\Documents\NetBeansProjects\Pharmacie-App\pharmacie.ico

[Languages]
Name: "french"; MessagesFile: "compiler:Languages\French.isl"

[Tasks]
Name: "desktopicon"; Description: "Créer une icône sur le Bureau"; GroupDescription: "Raccourcis"; Flags: unchecked

[Files]
; Fichiers générés par NetBeans (dist)
Source: "C:\Users\PC\Documents\NetBeansProjects\Pharmacie-App\dist\Pharmacie-App.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\PC\Documents\NetBeansProjects\Pharmacie-App\dist\lib\*"; DestDir: "{app}\lib"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "C:\Users\PC\Documents\NetBeansProjects\Pharmacie-App\pharmacie.ico"; DestDir: "{app}"

; run.bat pris depuis le projet (pas dist)
Source: "C:\Users\PC\Documents\NetBeansProjects\Pharmacie-App\run.bat"; DestDir: "{app}"; Flags: ignoreversion

[Icons]
Name: "{autoprograms}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; WorkingDir: "{app}"
Name: "{autodesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; WorkingDir: "{app}"; Tasks: desktopicon
Name: "{autodesktop}\Pharmacie App"; Filename: "{app}\run.bat"; IconFilename: "{app}\pharmacie.ico"

[Run]
Filename: "{app}\{#MyAppExeName}"; WorkingDir: "{app}"; Description: "Lancer {#MyAppName}"; Flags: shellexec postinstall skipifsilent