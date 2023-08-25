package com.example.composearchitecture.screen

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composearchitecture.ui.theme.ComposeArchitectureTheme


// 단계 2: `ViewModel`을 상속받은 `ToDoViewModel`을 만듭니다.
// 첫 단계에서는 내용을 비워두고 시작합시다.
class ToDoViewModel : ViewModel(){
//    val text = mutableStateOf("")
    private val _text = MutableLiveData("")
    val text : LiveData<String> = _text
    val setText : (String) -> Unit = {
        _text.value = it
    }

    val key = mutableIntStateOf(-1)

    //mutableStateListOf -> 추가 삭제 대입 -> UI가 갱신된다.(각 항목의 필드가 변경되면 갱신되지 않는다.)
    //LiveData<List<X>>.observeAsState() -> List가 통채로 다른 list로 바뀌었을 때만 State가 갱신된다.
//    val toDoList = mutableStateListOf<ToDoData>()
    private val _rawToDoList = mutableListOf<ToDoData>()
    private val _toDoList = MutableLiveData<List<ToDoData>>()
    val toDoList : LiveData<List<ToDoData>> = _toDoList

    //toList, toMutableList 다 리스트 복사 방법
    val onSubmit: (String) -> Unit = {
        val key = key.value + 1
        _rawToDoList.add(ToDoData(key, it))
//        _toDoList.value = ArrayList(_rawToDoList)
        _toDoList.value = _rawToDoList.toMutableList()
        _text.value = ""
    }

    val onEdit: (Int, String) -> Unit = { key, newText ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList[i] = _rawToDoList[i].copy(text = newText)
        _toDoList.value = ArrayList<ToDoData>(_rawToDoList)
        _toDoList.value = mutableListOf<ToDoData>().also {
            it.addAll(_rawToDoList) //이게 더 빠를 가능성이 크다.
            // shallow copy를 염두해야 한다.
        }
        _toDoList.value = _rawToDoList.toMutableList()
    }

    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList[i] = _rawToDoList[i].copy(done = checked)
        _toDoList.value = ArrayList(_rawToDoList)
    }

    val onDelete: (Int) -> Unit = { key ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList.removeAt(i)
        _toDoList.value = ArrayList(_rawToDoList)
    }
}


// 단계 3: `TopLevel`의 파라미터로 `ToDoViewModel` 타입의
// `viewModel`을 전달합니다. 기본 값은 `viewModel()`로 설정합시다.
// 에러가 발생하면 아래의 `import` 문을 추가합니다.
// `import androidx.lifecycle.viewmodel.compose.viewModel`


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ViewModelScreen(
    navController: NavController,
    viewModel: ToDoViewModel = viewModel()
){
// 단계 4: text, setText를 뷰 모델로 옮겨봅시다.
    // 뷰 모델의 프로퍼티로 변경할 경우에는 destrunction (비구조화,
    // 구조 분해)는 사용할 수 없으니 `by`를 써봅시다.
    // `remember`는 제거해야 합니다.
    // val (text, setText) = remember { mutableStateOf("") }

    // 단계 5: `toDoList`, `onSubmit`, `onEdit`, `onToggle`,
    // `onDelete`를 모두 뷰 모델로 옮겨봅시다.

    Scaffold {
        Column {
            ToDoInput(
                text = viewModel.text.observeAsState("").value,
                onTextChange = viewModel.setText,
                onSubmit = viewModel.onSubmit
            )
            val items = viewModel.toDoList.observeAsState(emptyList()).value
            LazyColumn {
                items(
                    items = items,
                    key = { it.key }
                ) { toDoData ->
                    ToDo(
                        toDoData = toDoData,
                        onEdit = viewModel.onEdit,
                        onToggle = viewModel.onToggle,
                        onDelete = viewModel.onDelete
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ViewModelScreenPreview(){
    ViewModelScreen(rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoInput(
    text: String,
    onTextChange: (String) -> Unit,
    onSubmit: (String) -> Unit
) {
    Row(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = {
            onSubmit(text)
        }) {
            Text("입력")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoInputPreview() {
    ComposeArchitectureTheme {
        ToDoInput("테스트", {}, {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDo(
    toDoData: ToDoData,
    onEdit: (key: Int, text: String) -> Unit = { _, _ -> },
    onToggle: (key: Int, checked: Boolean) -> Unit = { _, _ -> },
    onDelete: (key: Int) -> Unit = {}
) {
    var isEditing by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.padding(4.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Crossfade(
            targetState = isEditing,
            label = ""
        ) {
            when (it) {
                false -> {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = toDoData.text,
                            modifier = Modifier.weight(1f)
                        )
                        Text("완료")
                        Checkbox(
                            checked = toDoData.done,
                            onCheckedChange = { checked ->
                                onToggle(toDoData.key, checked)
                            }
                        )
                        Button(
                            onClick = { isEditing = true }
                        ) {
                            Text("수정")
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(
                            onClick = { onDelete(toDoData.key) }
                        ) {
                            Text("삭제")
                        }
                    }
                }
                true -> {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val (text, setText) = remember { mutableStateOf(toDoData.text) }
                        OutlinedTextField(
                            value = text,
                            onValueChange = setText,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(onClick = {
                            isEditing = false
                            onEdit(toDoData.key, text)
                        }) {
                            Text("완료")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
    ComposeArchitectureTheme {
        ToDo(ToDoData(1, "nice", true))
    }
}

data class ToDoData(
    val key: Int,
    val text: String,
    val done: Boolean = false
)