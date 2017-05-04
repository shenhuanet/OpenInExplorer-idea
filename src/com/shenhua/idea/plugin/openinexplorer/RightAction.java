package com.shenhua.idea.plugin.openinexplorer;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;

/**
 * Created by shenhua on 5/3/2017.
 * Email shenhuanet@126.com
 */
public class RightAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        // TODO: insert action logic here
        Project project = event.getData(PlatformDataKeys.PROJECT);
        Object nav = event.getData(CommonDataKeys.NAVIGATABLE);
        String path;
        try {
            if (nav instanceof PsiDirectory) {
                PsiDirectory directory = (PsiDirectory) nav;
                path = directory.getVirtualFile().getPath();
            } else {
                PsiFile file = (PsiFile) nav;
                path = file.getVirtualFile().getPath();
            }
            Toast.make(project, MessageType.INFO, "Open: " + path);
            Runtime.getRuntime().exec("cmd /c start " + path);
        } catch (Exception e) {
            e.printStackTrace();
            if (nav instanceof PsiClass) {
                Toast.make(project, MessageType.ERROR, "Could not open the java file, double-click to open.");
                return;
            }

            Toast.make(project, MessageType.ERROR, e.getMessage());
        }
    }
}
